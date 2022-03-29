package com.diploma.edu.source.controllers;


import com.diploma.edu.source.model.Role;
import com.diploma.edu.source.servicies.RoleService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Role> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @PostMapping("/add")
    public boolean createRole(@RequestBody Role role) {
        return service.create(role);
    }

    @DeleteMapping("{id}")
    public boolean deleteRole(@PathVariable("id") BigInteger roleId) {
        return service.delete(roleId);
    }

    @PutMapping
    public boolean updateRole(@RequestBody Role role) {
        return service.update(role);
    }
}
