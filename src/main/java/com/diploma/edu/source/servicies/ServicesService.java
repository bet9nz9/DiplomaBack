package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicesService implements Service<com.diploma.edu.source.model.Service> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public ServicesService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public com.diploma.edu.source.model.Service getById(Long id) {
        return oracleDbAccess.getById(com.diploma.edu.source.model.Service.class, id);
    }

    @Override
    public boolean create(com.diploma.edu.source.model.Service object) {
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (oracleDbAccess.delete(com.diploma.edu.source.model.Service.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(com.diploma.edu.source.model.Service object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<com.diploma.edu.source.model.Service> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort) {
        return oracleDbAccess.selectPage(com.diploma.edu.source.model.Service.class, pageable, filter, sort);
    }
}
