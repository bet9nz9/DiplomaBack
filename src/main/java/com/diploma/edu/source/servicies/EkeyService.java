package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.diploma.edu.source.model.Ekey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
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
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
        if (oracleDbAccess.delete(Ekey.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Ekey object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Ekey> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Ekey.class, params);
    }
}
