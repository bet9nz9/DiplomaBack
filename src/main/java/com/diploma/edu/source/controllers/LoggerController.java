package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Logger;
import com.diploma.edu.source.servicies.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RequestMapping("logger")
@RestController
public class LoggerController {

    private final LoggerService service;

    @Autowired
    public LoggerController(LoggerService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Logger> getAll(@RequestParam Map<String, String> params) {

//        List<SearchCriteria> filters = new ArrayList<>();
//        Pageable pageable = null;
//        if (page == null && size != null) {
//            pageable = PageRequest.of(0, size);
//        }
//        if (page != null && size != null) {
//            pageable = PageRequest.of(page, size);
//        }
//        if (dateFrom != null) {
//            filters.add(new SearchCriteria("dateAndTime", " > to_date('" + changeDateFormat(new Date(dateFrom))
//                    + "', 'yyyy-mm-dd hh24:mi:ss')"));
//        }
//        if (dateTo != null) {
//            filters.add(new SearchCriteria("dateAndTime", " < to_date('" + changeDateFormat(new Date(dateTo))
//                    + "', 'yyyy-mm-dd hh24:mi:ss')"));
//        }
//        return loggerService.getAll(pageable, filters, new SortCriteria(sort));

        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @GetMapping("{id}")
    public Logger getLogger(@PathVariable("id") Long loggerID) {
        return service.getById(loggerID);
    }

    @PostMapping("/add")
    public boolean createLogger(@RequestBody Logger logger) {
        return service.create(logger);
    }

    private String changeDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
