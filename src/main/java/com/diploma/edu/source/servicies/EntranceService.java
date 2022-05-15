package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.entranceInteraction.EntranceActions;
import com.diploma.edu.source.model.Entrance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

@org.springframework.stereotype.Service
public class EntranceService implements Service<Entrance> {

    @Autowired
    private OracleDbAccess oracleDbAccess;

    @Override
    public Entrance getById(BigInteger id) {
        return oracleDbAccess.getById(Entrance.class, id);
    }

    @Override
    public boolean create(Entrance object) {
        if (oracleDbAccess.insert(object) == 1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean delete(BigInteger id) {
        if (oracleDbAccess.delete(Entrance.class, id) == 1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean update(Entrance entrance) {
        if (oracleDbAccess.update(entrance) == 1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Page<Entrance> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(Entrance.class, params);
    }

    public void interactWithEntrance(Map<String, String> params){
        EntranceActions.interact(params);
    }

}
