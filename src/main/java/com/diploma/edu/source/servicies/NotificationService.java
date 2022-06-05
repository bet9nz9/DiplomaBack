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
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Notification.class, id) == 1;
    }

    @Override
    public boolean update(Notification object) {
        return oracleDbAccess.update(object) == 1;
    }

    public List<String> getAllEmails(){
        return oracleDbAccess.getEmails();
    }

    @Override
    public Page<Notification> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Notification.class, params);
    }
}
