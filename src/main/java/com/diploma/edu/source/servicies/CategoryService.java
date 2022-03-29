package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.diploma.edu.source.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;

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
        if(oracleDbAccess.insert(object) ==1)
            return true;
        else return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        if(oracleDbAccess.delete(Category.class, id) ==1)
            return true;
        else return false;
    }

    @Override
    public boolean update(Category object) {
        if(oracleDbAccess.update(object) == 1)
            return true;
        else return false;
    }

    @Override
    public Page<Category> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort) {
        return oracleDbAccess.selectPage(Category.class, pageable, filter, sort);
    }

    public boolean getAllNotesById(BigInteger id){
        if(oracleDbAccess.getAllNotesById(id).size()>0)
            return true;
        else return false;
    }
}
