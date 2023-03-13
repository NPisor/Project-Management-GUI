package com.gui.webappgui.basePackage.controllers.startPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pmservice.basePackage.models.User.Users;
import com.pmservice.basePackage.repos.UsersRepo;

@Controller
public class StartPageController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/")
    public String index(@ModelAttribute Users user, Model model){
        if(user != null){
            model.addAttribute("users", usersRepo.findById(1L));
        }
        else{
            model.addAttribute("users", new Users());
        }
        return "index";
    }

}

