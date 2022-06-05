package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class AddressService implements Service<Address> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public AddressService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Address getById(BigInteger id) {
        return oracleDbAccess.getById(Address.class, id);
    }

    @Override
    public boolean create(Address object) {
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Address.class, id) == 1;
    }

    @Override
    public boolean update(Address object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<Address> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Address.class, params);
    }
}
