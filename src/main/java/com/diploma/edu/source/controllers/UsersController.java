package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.User;
import com.diploma.edu.source.servicies.UsersService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public Page<User> getAll(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "firstName", required = false) String firstName,
                             @RequestParam(value = "lastName", required = false) String lastName,
                             @RequestParam(value = "patronymic", required = false) String patronymic,
                             @RequestParam(value = "isActive", required = false) String isActive,
                             @RequestParam(value = "receiveUtilityNotification", required = false) String receiveUtilityNotification,
                             @RequestParam(value = "roleID", required = false) String roleID,
                             @RequestParam(value = "sort", required = false) String sort) {
        List<SearchCriteria> filters = new ArrayList<>();
        Pageable pageable = null;
        if (page == null && size != null) {
            pageable = PageRequest.of(0, size);
        }
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        if (email != null) {
            filters.add(new SearchCriteria("email", "like '%" + email + "%' "));
        }
        if (firstName != null) {
            filters.add(new SearchCriteria("firstName", "like '%" + firstName + "%' "));
        }
        if (lastName != null) {
            filters.add(new SearchCriteria("lastName", "like '%" + lastName + "%' "));
        }
        if (patronymic != null) {
            filters.add(new SearchCriteria("patronymic", "like '%" + patronymic + "%' "));
        }
        if (isActive != null) {
            filters.add(new SearchCriteria("isActive", "like '%" + isActive + "%' "));
        }
        if (receiveUtilityNotification != null) {
            filters.add(new SearchCriteria("receiveUtilityNotification", "like '%" + receiveUtilityNotification + "%' "));
        }
        if (roleID != null) {
            filters.add(new SearchCriteria("roleID", roleID));
        }
        return service.getAll(pageable, filters, new SortCriteria(sort));
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
    public boolean deleteUser(@PathVariable("id") Long userId) {
        return service.delete(userId);
    }

    @RequestMapping(value = "/get-one/{id}")
    public User getOne(@PathVariable("id") Long id) {
        return service.getById(id);
    }
}
