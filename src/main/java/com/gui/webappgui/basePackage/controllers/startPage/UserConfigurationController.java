package com.gui.webappgui.basePackage.controllers.startPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmservice.basePackage.models.User.Users;
import com.pmservice.basePackage.repos.UsersRepo;


@Controller
public class UserConfigurationController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/config")
    public String userConfig(){
        return "ConfigPage";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public String createNewUser(Users newUser) throws JsonProcessingException
    {
        ObjectMapper om = new ObjectMapper();
        if(usersRepo.findById(newUser.getId())==null){
            usersRepo.save(newUser);
            return om.writeValueAsString("User: " + newUser.getId() + " created successfully.");
        }
        else{
            return om.writeValueAsString("Error: User already exists.");
        }
    }
    
}
