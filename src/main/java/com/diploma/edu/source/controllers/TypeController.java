package com.diploma.edu.source.controllers;


import com.diploma.edu.source.model.Type;
import com.diploma.edu.source.servicies.TypeService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("type")
@RestController
public class TypeController {

    TypeService service;

    public TypeController(TypeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Type> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @PostMapping("/add")
    public boolean createType(@RequestBody Type type) {
        return service.create(type);
    }

    @DeleteMapping("{id}")
    public boolean deleteType(@PathVariable("id") BigInteger typeId) {
        return service.delete(typeId);
    }

    @PutMapping
    public boolean updateType(@RequestBody Type type) {
        return service.update(type);
    }
}
