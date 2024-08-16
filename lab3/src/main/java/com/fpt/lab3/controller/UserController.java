package com.fpt.lab3.controller;


import com.fpt.lab3.model.User;
import com.fpt.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "/user/logout-success";
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "/user/access-denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/user/login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Model model) {
        String ssoId = getPrincipal();
        String message = "Xin chào bạn, " + ssoId;
        model.addAttribute("message", message);
        return "/user/user-profile";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/user/add-user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        userService.add(user);
        return "redirect:/user/profile";
    }


    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails detail = (UserDetails) principal;
        if (detail != null) {
            userName = detail.getUsername();
        }
        return userName;
    }
}
