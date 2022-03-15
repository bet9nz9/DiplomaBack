package com.diploma.edu.source.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
    /**
     * present SecurityContextHolder.getContext() available methods:
     *
     *   - Get the username of the logged in user: getPrincipal()
     *   - Get the password of the authenticated user: getCredentials()
     *   - Get the assigned roles of the authenticated user: getAuthorities()
     *   - Get further details of the authenticated user: getDetails()
     */

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication(); //gets username (email) of current user

        return authentication.toString();
    }
}