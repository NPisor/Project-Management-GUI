package com.gui.webappgui.basePackage.controllers.startPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pmservice.basePackage.repos.UsersRepo;

@Controller
public class UserConfigurationController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/config")
    public String userConfig(){
        return "ConfigPage";
    }
    
}
