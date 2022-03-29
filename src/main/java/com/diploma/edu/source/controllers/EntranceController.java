package com.diploma.edu.source.controllers;

import com.diploma.edu.source.db.access.OracleDbAccess;
import com.diploma.edu.source.model.Entrance;
import com.diploma.edu.source.model.Logger;
import com.diploma.edu.source.model.Notification;
import com.lowagie.text.DocumentException;
import com.diploma.edu.source.servicies.EntranceService;
import com.diploma.edu.source.servicies.ExportPDFService;
import com.diploma.edu.source.servicies.LoggerService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Autowired
    private OracleDbAccess oracleDbAccess;

    @GetMapping(value = "/open",
            params = {"key_id", "entrance_id"})
    public boolean openGate(@RequestParam("key_id") Long key_id, @RequestParam("entrance_id") Long entrance_id) {
        //will return isOpened
        return false;
    }

    @GetMapping(value = "/block",
            params = {"key_id", "entrance_id"})
    public boolean blockGate(@RequestParam("key_id") Long key_id, @RequestParam("entrance_id") Long entrance_id) {
        //will return isBlock
        return false;
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
        Page<Entrance> page1 = service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));

        return page1;
    }

    @GetMapping("/log")
    public void getLog(@RequestParam("page") int page, @RequestParam("size") int size,
                       @RequestParam(value = "filter", required = false) String filter,
                       @RequestParam(value = "sort", required = false) String sort) {

    }

    @RequestMapping(value = "/get-one/{id}")
    public Entrance getOne(@PathVariable("id") BigInteger id) {
        return service.getById(id);
    }

    //TODO: поменять этот метод
    @GetMapping("/export")
    public void exportToPDF(HttpServletResponse response,
                            @RequestParam(value = "dateFrom", required = false) Long dateFrom,
                            @RequestParam(value = "dateTo", required = false) Long dateTo,
                            @RequestParam(value = "filter", required = false) String filter) throws DocumentException, IOException {

        List<SearchCriteria> filters = new ArrayList<>();
        if (dateFrom != null) {
            filters.add(new SearchCriteria("dateAndTime", " > to_date('" + changeDateFormat(new Date(dateFrom))
                    + "', 'yyyy-mm-dd hh24:mi:ss')"));
        }
        if (dateTo != null) {
            filters.add(new SearchCriteria("dateAndTime", " < to_date('" + changeDateFormat(new Date(dateTo))
                    + "', 'yyyy-mm-dd hh24:mi:ss')"));
        }
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = entrance_" + currentDateTime + ".pdf";

        response.setHeader(headerKey,headerValue);

        SortCriteria sortCriteria = new SortCriteria("isActive:DESC");
        Page<Logger> pageEntrance = loggerService.getAll(null,filters,null);
        List<Logger> loggerList = pageEntrance.getContent();

        ExportPDFService exporter = new ExportPDFService(loggerList);
        exporter.export(response);
    }

    private String changeDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
