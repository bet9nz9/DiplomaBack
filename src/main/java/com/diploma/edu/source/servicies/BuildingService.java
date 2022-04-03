package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.diploma.edu.source.model.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class BuildingService implements Service<Building> {

    private final OracleDbAccess oracleDbAccess;

    public BuildingService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Building getById(BigInteger id) {
        return oracleDbAccess.getById(Building.class, id);
    }

    @Override
    public boolean create(Building object) {
        if (oracleDbAccess.insert(object) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
        if (oracleDbAccess.delete(Building.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Building object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Building> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Building.class, params);
    }
}
