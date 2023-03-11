package com.gui.webappgui.basePackage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmservice.basePackage.models.User.Users;
import com.pmservice.basePackage.repos.UsersRepo;

@Controller
public class testController {

    @Autowired
    UsersRepo usersRepo;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public String getUsers(@ModelAttribute Users user ,Model model) {
        model.addAttribute("users", usersRepo.findAll());
        System.out.println(usersRepo.findAll());
        return "testPage";
    }
}
