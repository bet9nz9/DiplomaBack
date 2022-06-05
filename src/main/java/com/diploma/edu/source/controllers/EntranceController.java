package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Entrance;
import com.diploma.edu.source.servicies.EntranceService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;

/**
 * Если запрос подразумевает фильтр, то строковые переменные типа имени надо привети к виду "like '%"+name+"%'" иначе не сработает
 * Фильтры записываются в список, не смотря на их количество и передаются дальше
 * Указывать несколько критериев сортировки нельзя
 */

@RequestMapping("/entrance")
@RestController
public class EntranceController {

    private final EntranceService service;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EntranceController.class.getName());

    public EntranceController(EntranceService service) {
        this.service = service;
    }

    @GetMapping("/interact")
    public boolean openGate(@RequestParam Map<String, String> params) {
        service.interactWithEntrance(params);
        return true;
    }

    @PostMapping("/add")
    public boolean createEntrance(@RequestBody Entrance entrance) {
        return service.create(entrance);
    }

    @DeleteMapping(params = {"id"})
    public boolean deleteEntrance(@RequestParam("id") BigInteger entranceId) {
        return service.delete(entranceId);
    }

    @PutMapping
    public boolean updateEntrance(@RequestBody Entrance object) {
        return service.update(object);
    }

    @GetMapping
    public Page<Entrance> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(params);
    }

    @RequestMapping(value = "/get-one/{id}")
    public Entrance getOne(@PathVariable("id") BigInteger id) {
        return service.getById(id);
    }
}
