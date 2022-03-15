package com.diploma.edu.source.controllers;


import com.diploma.edu.source.model.Type;
import com.diploma.edu.source.servicies.TypeService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("type")
@RestController
public class TypeController {

    TypeService service;

    public TypeController(TypeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Type> getAll(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "value", required = false) String value,
                             @RequestParam(value = "sort", required = false) String sort) {
        List<SearchCriteria> filters = new ArrayList<>();
        Pageable pageable = null;
        if (page == null && size != null) {
            pageable = PageRequest.of(0, size);
        }
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        if (value != null) {
            filters.add(new SearchCriteria("value", value));
        }
        return service.getAll(pageable, filters, new SortCriteria(sort));
    }

    @PostMapping("/add")
    public boolean createType(@RequestBody Type type) {
        return service.create(type);
    }

    @DeleteMapping("{id}")
    public boolean deleteType(@PathVariable("id") Long typeId) {
        return service.delete(typeId);
    }

    @PutMapping
    public boolean updateType(@RequestBody Type type) {
        return service.update(type);
    }
}
