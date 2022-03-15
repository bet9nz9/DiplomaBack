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
import java.util.ArrayList;
import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Category> getAll(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size,
                                      @RequestParam(value = "number", required = false) String number,
                                      @RequestParam(value = "sort", required = false) String sort) {
        List<SearchCriteria> filters = new ArrayList<>();
        Pageable pageable = null;
        if (page == null && size != null) {
            pageable = PageRequest.of(0, size);
        }
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        if (number != null) {
            filters.add(new SearchCriteria("number", number));
        }
        return service.getAll(pageable, filters, new SortCriteria(sort));
    }

    @GetMapping("/get-one/{id}")
    public Category getOne(@PathVariable("id") Long id){
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
    public boolean deleteCategory(@PathVariable("id") Long categoryId){
        if(service.getAllNotesById(Math.toIntExact(categoryId)))
            return false;
        else return service.delete(categoryId);
    }
}
