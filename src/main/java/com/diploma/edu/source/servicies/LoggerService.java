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
        if (oracleDbAccess.insert(object) == 1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
        if (oracleDbAccess.delete(Logger.class, id) == 1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean update(Logger entrance) {
        if (oracleDbAccess.update(entrance) == 1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Page<Logger> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Logger.class, params);
    }
}
