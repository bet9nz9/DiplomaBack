package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Category;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class CategoryService implements Service<Category> {

    private final OracleDbAccess oracleDbAccess;

    public CategoryService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public Category getById(BigInteger id) {
        return oracleDbAccess.getById(Category.class, id);
    }

    @Override
    public boolean create(Category object) {
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(Category.class, id) == 1;
    }

    @Override
    public boolean update(Category object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<Category> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Category.class, params);
    }

    public boolean getAllNotesById(BigInteger id) {
        return oracleDbAccess.getAllNotesById(id).equals(new BigInteger("0"));
    }
}
