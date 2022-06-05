package com.diploma.edu.source.controllers;

import com.diploma.edu.source.dto.AuthRequest;
import com.diploma.edu.source.dto.AuthResponse;
import com.diploma.edu.source.model.ActivationMessage;
import com.diploma.edu.source.model.User;
import com.diploma.edu.source.servicies.MailSenderService;
import com.diploma.edu.source.servicies.TokenService;
import com.diploma.edu.source.servicies.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@RestController
public class AuthController {
    private static final String AUTHORIZATION = "Authorization";

    private final UsersService usersService;
    private final TokenService tokenService;
    private final MailSenderService mailSenderService;

    @Value("${server.port}")
    String serverPortName;

    @Autowired
    public AuthController(UsersService usersService, TokenService tokenService, MailSenderService mailSenderService) {
        this.usersService = usersService;
        this.tokenService = tokenService;
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/register")
    public boolean registration(@RequestBody User user) {
        try{
            usersService.findUserByEmail(user.getEmail());
            return false;
        }catch (IndexOutOfBoundsException e){
            user.setActivationCode(UUID.randomUUID().toString());
            String message = String.format(
                    "Hello, %s! \n" +
                            "You are welcome! Please, visit next link: http://localhost:%s/activate/%s",
                    user.getLastName(),
                    4200,
                    user.getActivationCode()
            );
            mailSenderService.sendEmail(user.getEmail(), "Activation code", message);
            return usersService.create(user);
        }

    }

    @GetMapping("/activate/{code}")
    public ActivationMessage activate(@PathVariable String code){
        boolean isActivated = usersService.activateUser(code);
        ActivationMessage activationMessage;
        if (isActivated) {
            activationMessage = new ActivationMessage("Success", "Аккаунт активирован!");
        } else {
            activationMessage = new ActivationMessage("Danger", "Аккаунт уже был активирован, выполните вход!");
        }
        return activationMessage;
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = usersService.findByLoginAndPass(request.getEmail(), request.getPassword());
        String token = tokenService.createToken(user.getEmail());
        return new AuthResponse(token);
    }

    @GetMapping("/current")
    public User currentUser(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader(AUTHORIZATION);
        String userEmail = tokenService.getUserEmailFromToken(token);
        return usersService.findUserByEmail(userEmail);
    }
}
