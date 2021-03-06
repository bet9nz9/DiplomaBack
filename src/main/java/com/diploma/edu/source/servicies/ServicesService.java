package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.exceptions.IncorrectDataException;
import com.diploma.edu.source.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServicesService implements Service<com.diploma.edu.source.model.Service> {

    private final OracleDbAccess oracleDbAccess;

    @Autowired
    public ServicesService(OracleDbAccess oracleDbAccess) {
        this.oracleDbAccess = oracleDbAccess;
    }

    @Override
    public com.diploma.edu.source.model.Service getById(BigInteger id) {
        return oracleDbAccess.getById(com.diploma.edu.source.model.Service.class, id);
    }

    @Override
    public boolean create(com.diploma.edu.source.model.Service object) {
        checkIsUtilityUnique(object);
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(com.diploma.edu.source.model.Service.class, id) == 1;
    }

    @Override
    public boolean update(com.diploma.edu.source.model.Service object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<com.diploma.edu.source.model.Service> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(com.diploma.edu.source.model.Service.class, params);
    }

    public Page<ServiceType> getServicesTypes(Map<String, String> params) {
        return oracleDbAccess.selectPage(ServiceType.class, params);
    }

    private void checkIsUtilityUnique(com.diploma.edu.source.model.Service service){
        Map<String, String> params = new HashMap<>();
        params.put("address", service.getAddress().getId().toString());
        params.put("serviceType", service.getServiceType().getId().toString());

        Page<com.diploma.edu.source.model.Service> page = oracleDbAccess.selectPage(com.diploma.edu.source.model.Service.class, params);

        if (!page.getContent().isEmpty()){
            throw new IncorrectDataException("???????????? ?????? ???????????????????????? ???????????? ?????? ???????????????????? ?????? ?????????? ????????????!");
        }

    }

}
