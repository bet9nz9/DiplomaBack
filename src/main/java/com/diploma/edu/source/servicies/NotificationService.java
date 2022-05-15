package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class NotificationService implements Service<Notification> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public NotificationService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Notification getById(BigInteger id) {
        return oracleDbAccess.getById(Notification.class, id);
    }

    @Override
    public boolean create(Notification object) {
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
        if (oracleDbAccess.delete(Notification.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Notification object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getAllEmails(){
        return oracleDbAccess.getEmails();
    }

    @Override
    public Page<Notification> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Notification.class, params);
    }
}
