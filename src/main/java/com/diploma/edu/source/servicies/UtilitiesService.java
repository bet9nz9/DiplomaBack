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
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
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
    public Page<Utility> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Utility.class, params);
    }
}
