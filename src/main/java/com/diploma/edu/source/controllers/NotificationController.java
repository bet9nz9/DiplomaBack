package com.diploma.edu.source.controllers;

import com.diploma.edu.source.model.Notification;
import com.diploma.edu.source.servicies.MailSenderService;
import com.diploma.edu.source.servicies.NotificationService;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RequestMapping("notification")
@RestController
public class NotificationController {
    private final NotificationService service;
    private final MailSenderService mailSenderService;

    @Value("${server.port}")
    String serverPortName;

    @Autowired
    public NotificationController(NotificationService service, MailSenderService mailSenderService) {
        this.service = service;
        this.mailSenderService = mailSenderService;
    }

    @GetMapping
    public Page<Notification> getAll(@RequestParam Map<String, String> params) {
        Page<Notification> page1 = service.getAll(GetRequestParams.getPageable(params),
                GetRequestParams.getFilters(params),
                GetRequestParams.getSortCriteria(params));

        return page1;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteNotification(@PathVariable("id") BigInteger id) {
        return service.delete(id);
    }

    @PostMapping("/add")
    public boolean createNotification(@RequestBody Notification notification) {
        service.create(notification);
        List<String> emails = service.getAllEmails();
        emails.add("victormorgish@gmail.com");
        for (String email: emails) {
            mailSenderService.sendEmail(email, notification.getTitle(), notification.getText());
        }
        return true;
    }

    @RequestMapping(value = "/get-one/{id}")
    public Notification getOne(@PathVariable("id") BigInteger id) {
        return service.getById(id);
    }

    @PostMapping("/utility/{utilityNotificationId}/{apartmentId}/{date}")
    public void postUtilityNotification(Long utilityId, Long utilityNotificationId, Long apartmentId, Date date) {

    }

    @RequestMapping(value="/get-users-notes/{id}")
    public Notification getUsersNotes(@PathVariable("id") Long id){
        return null;
    }

    private String changeDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
