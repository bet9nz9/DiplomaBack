package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Ekey;
import com.diploma.edu.source.servicies.EkeyService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("keys")
@RestController
public class ElectronicKeyController {

    private final EkeyService service;

    public ElectronicKeyController(EkeyService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Ekey> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @PostMapping("/add")
    public boolean createKey(@RequestBody Ekey ekey) {
        return service.create(ekey);
    }

    @DeleteMapping("{id}")
    public boolean deleteKey(@PathVariable("id") BigInteger keyID) {
        return service.delete(keyID);
    }

    @PutMapping
    public boolean updateKey(@RequestBody Ekey ekey) {
        return service.update(ekey);
    }

    @RequestMapping(value = "/get-one/{id}")
    public Ekey getOne(@PathVariable("id") BigInteger id) {
        return service.getById(id);
    }

}
