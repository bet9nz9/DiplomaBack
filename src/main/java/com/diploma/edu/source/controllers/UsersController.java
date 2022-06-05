package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.User;
import com.diploma.edu.source.servicies.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {


    private final UsersService service;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UsersService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Page<User> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(params);
    }

    @PostMapping("/add")
    public boolean createUser(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping
    public boolean updateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return service.update(user);
    }

    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable("id") BigInteger userId) {
        return service.delete(userId);
    }

    @RequestMapping(value = "/get-one/{id}")
    public User getOne(@PathVariable("id") BigInteger id) {
        return service.getById(id);
    }
}
