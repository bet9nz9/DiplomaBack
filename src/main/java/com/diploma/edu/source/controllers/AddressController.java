package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Address;
import com.diploma.edu.source.servicies.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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
        return service.getAll(params);
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
