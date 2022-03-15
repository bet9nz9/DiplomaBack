package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Logger;
import com.diploma.edu.source.servicies.LoggerService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("logger")
@RestController
public class LoggerController {

    private final LoggerService loggerService;

    @Autowired
    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping
    public Page<Logger> getAll(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size,
                               @RequestParam(value = "dateFrom", required = false) Long dateFrom,
                               @RequestParam(value = "dateTo", required = false) Long dateTo,
                               @RequestParam(value = "sort", required = false) String sort) {
        List<SearchCriteria> filters = new ArrayList<>();
        Pageable pageable = null;
        if (page == null && size != null) {
            pageable = PageRequest.of(0, size);
        }
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        if (dateFrom != null) {
            filters.add(new SearchCriteria("date", " > to_date('" + changeDateFormat(new Date(dateFrom))
                    + "', 'yyyy-mm-dd hh24:mi:ss')"));
        }
        if (dateTo != null) {
            filters.add(new SearchCriteria("date", " < to_date('" + changeDateFormat(new Date(dateTo))
                    + "', 'yyyy-mm-dd hh24:mi:ss')"));
        }
        return loggerService.getAll(pageable, filters, new SortCriteria(sort));
    }

    @GetMapping("{id}")
    public Logger getLogger(@PathVariable("id") Long loggerID) {
        return loggerService.getById(loggerID);
    }

    @PostMapping("/add")
    public boolean createLogger(@RequestBody Logger logger) {
        return loggerService.create(logger);
    }

    private String changeDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
