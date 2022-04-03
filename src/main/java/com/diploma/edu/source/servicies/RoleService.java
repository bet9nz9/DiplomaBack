package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Role;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class RoleService implements Service<Role> {

    private final OracleDbAccess oracleDbAccess;

    public RoleService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Role getById(BigInteger id) {
        return oracleDbAccess.getById(Role.class, id);
    }

    @Override
    public boolean create(Role object) {
        if (oracleDbAccess.insert(object) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
        if (oracleDbAccess.delete(Role.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Role object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Role> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Role.class, params);
    }
}
