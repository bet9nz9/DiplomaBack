package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Category;
import com.diploma.edu.source.servicies.CategoryService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("category")
@RestController
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Category> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @GetMapping("/get-one/{id}")
    public Category getOne(@PathVariable("id") BigInteger id){
        return service.getById(id);
    }

    @PostMapping("/add")
    public boolean createCategory(@RequestBody Category category){
        return service.create(category);
    }

    @PutMapping
    public boolean updateCategory(@RequestBody Category category){
        return service.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@PathVariable("id") BigInteger categoryId){
        if(!service.getAllNotesById(categoryId)){
            //TODO: пробросить ошибку, что у категории есть референсные нотификации
            return false;
        } else {
            return service.delete(categoryId);
        }
    }
}
