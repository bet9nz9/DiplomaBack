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

import java.util.ArrayList;
import java.util.List;

@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Role> getAll(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "role", required = false) String role,
                             @RequestParam(value = "sort", required = false) String sort) {
        List<SearchCriteria> filters = new ArrayList<>();
        Pageable pageable = null;
        if (page == null && size != null) {
            pageable = PageRequest.of(0, size);
        }
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        if (role != null) {
            filters.add(new SearchCriteria("flat", role));
        }
        return service.getAll(pageable, filters, new SortCriteria(sort));
    }

    @PostMapping("/add")
    public boolean createRole(@RequestBody Role role) {
        return service.create(role);
    }

    @DeleteMapping("{id}")
    public boolean deleteRole(@PathVariable("id") Long roleId) {
        return service.delete(roleId);
    }

    @PutMapping
    public boolean updateRole(@RequestBody Role role) {
        return service.update(role);
    }
}
