package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Utility;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class UtilitiesService implements Service<Utility> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public UtilitiesService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Utility getById(Long id) {
        return oracleDbAccess.getById(Utility.class, id);
    }

    @Override
    public boolean create(Utility object) {
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (oracleDbAccess.delete(Utility.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Utility object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Utility> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort) {
        return oracleDbAccess.selectPage(Utility.class, pageable, filter, sort);
    }
}
