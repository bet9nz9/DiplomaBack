package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Ekey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class EkeyService implements Service<Ekey> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public EkeyService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Ekey getById(BigInteger id) {
        return oracleDbAccess.getById(Ekey.class, id);
    }

    @Override
    public boolean create(Ekey object) {
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Ekey.class, id) == 1;
    }

    @Override
    public boolean update(Ekey object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<Ekey> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Ekey.class, params);
    }
}
