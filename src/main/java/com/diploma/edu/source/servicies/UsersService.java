package com.diploma.edu.source.servicies;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.exceptions.ResourceNotFoundException;
import com.diploma.edu.source.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class UsersService implements Service<User> {

    private final OracleDbAccess oracleDbAccess;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UsersService(OracleDbAccess oracleDbAccess, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.oracleDbAccess = oracleDbAccess;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User getById(BigInteger id) {
        return oracleDbAccess.getById(User.class, id);
    }

    @Override
    public boolean create(User object) {
        object.setRole(roleService.getById(new BigInteger("8")));
        object.setPassword(passwordEncoder.encode(object.getPassword()));
        return oracleDbAccess.insert(object) == 1;
    }

    @Override
    public boolean delete(BigInteger id) {
        return oracleDbAccess.delete(User.class, id) == 1;
    }

    @Override
    public boolean update(User object) {
        return oracleDbAccess.update(object) == 1;
    }

    @Override
    public Page<User> getAll(Map<String, String> params) {
        return oracleDbAccess.selectPage(User.class, params);
    }

    public User findUserByEmail(String email) throws IndexOutOfBoundsException{
        Map<String, String> params = new HashMap<>();
        params.put("email", email);

        return oracleDbAccess.selectPage(User.class, params)
                .getContent()
                .get(0);
    }

    public User findByActivatedCode(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("activationCode", code);

        List<User> users = oracleDbAccess.selectPage(User.class, params).getContent();

        if(!users.isEmpty()){
            return users.get(0);
        }else {
            return null;
        }
    }

    public boolean activateUser(String code) {
        User user = findByActivatedCode(code);

        if(user != null){
            user.setActivationCode(null);
            update(user);
            return true;
        }

        return false;
    }

    public User findByLoginAndPass(String login, String password)  {
        User user = findUserByEmail(login);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new ResourceNotFoundException();
    }
}
