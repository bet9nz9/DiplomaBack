package com.diploma.edu.source.controllers;


import com.diploma.edu.source.model.Type;
import com.diploma.edu.source.servicies.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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
        return service.getAll(params);
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
