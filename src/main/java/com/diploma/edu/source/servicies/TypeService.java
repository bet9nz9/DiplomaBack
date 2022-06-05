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
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Type.class, id) == 1;
    }

    @Override
    public boolean update(Type object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<Type> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Type.class, params);
    }
}
