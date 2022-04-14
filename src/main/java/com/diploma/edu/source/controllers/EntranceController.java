package com.diploma.edu.source.controllers;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Entrance;
import com.diploma.edu.source.model.Logger;
import com.diploma.edu.source.servicies.EntranceService;
import com.diploma.edu.source.servicies.ExportPDFService;
import com.diploma.edu.source.servicies.LoggerService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private final LoggerService loggerService;
    private final LoggerController loggerController;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EntranceController.class.getName());

    public EntranceController(EntranceService service, LoggerService loggerService, LoggerController loggerController) {
        this.service = service;
        this.loggerService = loggerService;
        this.loggerController = loggerController;
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

    private String changeDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
