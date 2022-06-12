package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class UtilitiesService implements Service<Utility> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public UtilitiesService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Utility getById(BigInteger id) {
        return oracleDbAccess.getById(Utility.class, id);
    }

    @Override
    public boolean create(Utility object) {
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Utility.class, id) == 1;
    }

    @Override
    public boolean update(Utility object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<Utility> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Utility.class, params);
    }
}
