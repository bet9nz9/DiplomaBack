package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;


@org.springframework.stereotype.Service
public class TypeService implements Service<Type> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public TypeService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Type getById(BigInteger id) {
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
    public boolean delete(BigInteger id) {
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
    public Page<Type> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Type.class, params);
    }
}
