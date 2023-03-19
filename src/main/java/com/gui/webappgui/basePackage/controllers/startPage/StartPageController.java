package com.gui.webappgui.basePackage.controllers.startPage;

import java.util.Optional;

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
    public String index(@ModelAttribute Optional<Users> user, Model model) throws Exception{
        model.addAttribute("users", usersRepo.findById(4L).get());
        return "index";
    } 

}

