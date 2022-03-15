package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.diploma.edu.source.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class AddressService implements Service<Address> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public AddressService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Address getById(Long id) {
        return oracleDbAccess.getById(Address.class, id);
    }

    @Override
    public boolean create(Address object) {
        if (oracleDbAccess.insert(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (oracleDbAccess.delete(Address.class, id) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Address object) {
        if (oracleDbAccess.update(object) == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Address> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort) {
        return oracleDbAccess.selectPage(Address.class, pageable, filter, sort);
    }
}
