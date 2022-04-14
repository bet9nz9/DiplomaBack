package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Logger;
import com.diploma.edu.source.servicies.ExportPDFService;
import com.diploma.edu.source.servicies.LoggerService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        return service.getAll(params);
    }

    @GetMapping("{id}")
    public Logger getLogger(@PathVariable("id") BigInteger loggerID) {
        return service.getById(loggerID);
    }

    @PostMapping("/add")
    public boolean createLogger(@RequestBody Logger logger) {
        return service.create(logger);
    }

    @GetMapping("/export")
    public void exportToPDF(HttpServletResponse response,
                            @RequestParam Map<String, String> params) throws DocumentException, IOException {

        List<Logger> loggersList = service.getAll(params).getContent();

        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = log_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        ExportPDFService exporter = new ExportPDFService(loggersList);
        exporter.export(response);
    }

}
