package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Service;
import com.diploma.edu.source.model.ServiceType;
import com.diploma.edu.source.model.Utility;
import com.diploma.edu.source.servicies.ServicesService;
import com.diploma.edu.source.servicies.UtilitiesCalculator;
import com.diploma.edu.source.servicies.UtilitiesService;
import com.diploma.edu.source.validators.UtilityReadingsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;


@RequestMapping("utilities")
@RestController
public class UtilitiesController {


    private final UtilitiesService service;
    private final ServicesService servicesService;

    @Autowired
    public UtilitiesController(UtilitiesService service, ServicesService servicesService) {
        this.service = service;
        this.servicesService = servicesService;
    }

    @GetMapping
    public Page<Utility> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(params);
    }

    @GetMapping("{id}")
    public Utility getUtility(@PathVariable("id") BigInteger utilityID) {
        return service.getById(utilityID);
    }

    @GetMapping("/userServices")
    public Page<Service> getServices(@RequestParam Map<String, String> params) {
        return servicesService.getAll(params);
    }

    @PostMapping("/createUserService")
    public boolean createUserService(@RequestBody Service service) {
        return servicesService.create(service);
    }

    @DeleteMapping("/userServices/{id}")
    public boolean deleteUserService(@PathVariable("id") BigInteger id) {
        return servicesService.delete(id);
    }

    @PutMapping("/userServices/update")
    public boolean updateUserService(@RequestBody Service service) {
        return servicesService.update(service);
    }

    @GetMapping("/services")
    public Page<ServiceType> getServicesTypes(@RequestParam Map<String, String> params) {
        return servicesService.getServicesTypes(params);
    }

    @PostMapping("/add")
    public boolean createUtility(@RequestBody Utility utility) {
        return service.create(utility);
    }

    @PutMapping
    public boolean updateUtility(@RequestBody Utility utility) {
        UtilityReadingsValidator.validate(utility);

        Utility newUtility = utility.copy();
        newUtility.setEndMonthReading(null);
        newUtility.setStartMonthReading(utility.getEndMonthReading());

        UtilitiesCalculator.calculateUtility(utility);

        service.update(utility);
        return service.create(newUtility);
    }
}
