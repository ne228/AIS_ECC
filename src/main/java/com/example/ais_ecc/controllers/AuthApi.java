package com.example.ais_ecc.controllers;

import com.example.ais_ecc.JwtTokenUtil;
import com.example.ais_ecc.entity.User;
import com.example.ais_ecc.models.AuthRequest;
import com.example.ais_ecc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
public class AuthApi {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    UserRepository userRepository;


   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        model.addAttribute("authRequest", new AuthRequest());
        return "login"; // возвращаем шаблон для GET запроса
    }

    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    public String login(@ModelAttribute("authRequest") @Valid AuthRequest request,
                        BindingResult bindingResult,
                        HttpServletRequest servletRequest,
                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "login"; // Вернуться на страницу входа с сообщением об ошибке
        }

        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/home"; // Редирект на вашу домашнюю страницу
        } catch (Exception ex) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Вернуться на страницу входа с сообщением об ошибке
        }
    }


    @PermitAll
    @GetMapping ("/auth/logout")
    public String logout(AuthRequest request, HttpServletRequest servletRequestrequest, Model model) {

        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }


    @GetMapping("/auth/test")
    public String test() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        var roles = user.getAuthorities();
        var roles2 = user.getRoles();
        var result = "roles by userRoles:" + user.getRoles().toString() + "\nRolesByAuthorites" + roles.toArray().toString() + "\n" + user.getUsername();
        return result;

    }


    @RolesAllowed("ROLE_USER")
    @GetMapping("/auth/user")
    public String user() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        return user.getUsername();

    }

    @RolesAllowed("ROLE_INVESTIGATOR")
    @GetMapping("/auth/investigator")
    public String investigator() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        return user.getUsername();

    }


    @RolesAllowed("ROLE_PROSECUTOR")
    @GetMapping("/auth/prosecutor")
    public String prosecutor() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        return user.getUsername();

    }
}


// LOGIN FOR JWT
//    @PermitAll
//    @PostMapping("/auth/login")
//    public ResponseEntity<?> login(AuthRequest request) {
//        try {
//
//            Authentication authentication = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(), request.getPassword())
//            );
//
//            User user = (User) authentication.getPrincipal();
//            String accessToken = jwtUtil.generateAccessToken(user);
//            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
//
//            return ResponseEntity.ok().body(response);
//
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//    }