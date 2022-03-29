package com.diploma.edu.source.controllers;


import com.diploma.edu.source.model.Building;
import com.diploma.edu.source.servicies.BuildingService;
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

@RequestMapping("building")
@RestController
public class BuildingController {

    private final BuildingService service;

    @Autowired
    public BuildingController(BuildingService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Building> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @PostMapping("/add")
    public boolean createBuilding(@RequestBody Building building) {
        return service.create(building);
    }

    @GetMapping("{id}")
    public Building getBuildingById(@PathVariable("id") BigInteger buildingID) {
        return service.getById(buildingID);
    }

    @DeleteMapping("{id}")
    public boolean deleteBuilding(@PathVariable("id") BigInteger buildingID) {
        return service.delete(buildingID);
    }

    @PutMapping
    public boolean updateBuilding(@RequestBody Building building) {
        return service.update(building);
    }
}
