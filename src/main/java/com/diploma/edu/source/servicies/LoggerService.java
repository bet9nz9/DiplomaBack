package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class LoggerService implements Service<Logger>{

    @Autowired
    private OracleDbAccess oracleDbAccess;

    @Override
    public Logger getById(BigInteger id) {
        return oracleDbAccess.getById(Logger.class, id);
    }

    @Override
    public boolean create(Logger object) {
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Logger.class, id) == 1;
    }

    @Override
    public boolean update(Logger entrance) {
        return oracleDbAccess.update(entrance) == 1;
    }

    @Override
    public Page<Logger> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Logger.class, params);
    }
}
