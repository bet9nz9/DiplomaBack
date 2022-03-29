package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Utility;
import com.diploma.edu.source.model.Service;
import com.diploma.edu.source.servicies.ServicesService;
import com.diploma.edu.source.servicies.UtilitiesService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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
        return service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));
    }

    @GetMapping("{id}")
    public Utility getUtility(@PathVariable("id") BigInteger utilityID) {
        return service.getById(utilityID);
    }

    @GetMapping("/services")
    public Page<Service> getServices(){
        return servicesService.getAll(null, null, null);
    }

    @PostMapping("/add")
    public boolean createUtility(@RequestBody Utility utility) {
        return service.create(utility);
    }

    @PutMapping
    public boolean updateUtility(@RequestBody Utility utility) {
        //TODO: заменить проверку и расчет
        if (utility.getEndMonthReading() == null || utility.getStartMonthReading() == null) {
            Utility utilityFromDB = service.getById(utility.getId());
            if (utility.getEndMonthReading() != null) {
                if (utility.getEndMonthReading().intValue() > utilityFromDB.getStartMonthReading().intValue()) {
                    utility.setAmountToPay(
                            (utility.getEndMonthReading().intValue() - utility.getStartMonthReading().intValue())
                                    * utility.getService().getTariff()
                    );
                    utility.setStatus(true);
                    /**
                     * Создание новой записи, при условии, что происходит внесение новых записей
                     * */
                    Utility newUtility = new Utility();
                    newUtility.setDateAndTime(monthIncrement(utility.getDateAndTime()));
                    newUtility.setStartMonthReading(utility.getEndMonthReading());
                    newUtility.setService(utility.getService());
                    newUtility.setStatus(false);
                    service.create(newUtility);

                }
            }
        }

        return service.update(utility);
    }

    private String changeDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private Date monthIncrement(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    private String getMonthAndYear(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM");
        return dateFormat.format(date);
    }

}
