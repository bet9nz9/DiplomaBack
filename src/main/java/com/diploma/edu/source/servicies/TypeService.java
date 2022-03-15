package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Type;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@org.springframework.stereotype.Service
public class TypeService implements Service<Type>{

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public TypeService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Type getById(Long id) {
        return oracleDbAccess.getById(Type.class, id);
    }

    @Override
    public boolean create(Type object) {
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (oracleDbAccess.delete(Type.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Type object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Type> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort) {
        return oracleDbAccess.selectPage(Type.class, pageable, filter, sort);
    }
}
