package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Ekey;
import com.diploma.edu.source.servicies.EkeyService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("keys")
@RestController
public class ElectronicKeyController {

    private final EkeyService service;

    public ElectronicKeyController(EkeyService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Ekey> getAll(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "keyCode", required = false) String keyCode,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "isActive", required = false) String isActive,
                             @RequestParam(value = "userId", required = false) String userId,
                             @RequestParam(value = "sort", required = false) String sort) {
        List<SearchCriteria> filters = new ArrayList<>();
        Pageable pageable = null;
        if (page == null && size != null) {
            pageable = PageRequest.of(0, size);
        }
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        if (keyCode != null) {
            filters.add(new SearchCriteria("keyCode", "like '%" + keyCode + "%' "));
        }
        if (name != null) {
            filters.add(new SearchCriteria("name", "like '%" + name + "%' "));
        }
        if (isActive != null) {
            filters.add(new SearchCriteria("isActive", "like '%" + isActive + "%' "));
        }
        if (userId != null) {
            filters.add(new SearchCriteria("user", userId));
        }
        return service.getAll(pageable, filters, new SortCriteria(sort));
    }

    @PostMapping("/add")
    public boolean createKey(@RequestBody Ekey ekey) {
        return service.create(ekey);
    }

    @DeleteMapping("{id}")
    public boolean deleteKey(@PathVariable("id") Long keyID) {
        return service.delete(keyID);
    }

    @PutMapping
    public boolean updateKey(@RequestBody Ekey ekey) {
        return service.update(ekey);
    }

    @RequestMapping(value = "/get-one/{id}")
    public Ekey getOne(@PathVariable("id") Long id) {
        return service.getById(id);
    }

}
