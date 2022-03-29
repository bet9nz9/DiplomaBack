package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Address;
import com.diploma.edu.source.model.Notification;
import com.diploma.edu.source.servicies.AddressService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("address")
@RestController
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Address> getAll(@RequestParam Map<String, String> params) {

//        List<SearchCriteria> filters = new ArrayList<>();
//        Pageable pageable = null;
//        if (page == null && size != null) {
//            pageable = PageRequest.of(0, size);
//        }
//        if (page != null && size != null) {
//            pageable = PageRequest.of(page, size);
//        }
//        if (flat != null) {
//            filters.add(new SearchCriteria("flat", flat));
//        }
//        if (building != null) {
//            filters.add(new SearchCriteria("building",building));
//        }
//        if (user != null) {
//            filters.add(new SearchCriteria("user",user));
//        }
        //return service.getAll(pageable, filters, new SortCriteria(sort));

        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @GetMapping("{id}")
    public Address getUtility(@PathVariable("id") BigInteger addressID) {
        return service.getById(addressID);
    }

    @PostMapping("/add")
    public boolean createAddress(@RequestBody Address address) {
        return service.create(address);
    }

    @DeleteMapping("{id}")
    public boolean deleteAddress(@PathVariable("id") BigInteger addressId) {
        return service.delete(addressId);
    }

    @PutMapping
    public boolean updateAddress(@RequestBody Address address) {
        return service.update(address);
    }

}
