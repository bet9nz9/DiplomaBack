package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Building;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
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
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Building.class, id) == 1;
    }

    @Override
    public boolean update(Building object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<Building> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Building.class, params);
    }
}
