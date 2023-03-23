package com.gui.webappgui.basePackage.controllers.startPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmservice.basePackage.models.User.Users;
import com.pmservice.basePackage.repos.UsersRepo;


@Controller
@CrossOrigin(origins = "*")
public class UserConfigurationController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/config")
    public String userConfig(){
        return "ConfigPage";
    }

    
    @PostMapping("/createUser")
    @ResponseBody
    public String createNewUser(Users newUser) throws Exception
    {
        System.out.println(newUser);
        ObjectMapper om = new ObjectMapper();
        if(usersRepo.findById(Long.valueOf(newUser.getId())).isEmpty()){
            usersRepo.save(newUser);
            return om.writeValueAsString("User: " + newUser.getId() + " created successfully.");
        }
        else{
            throw new Exception("User already exists in database with ID: " + newUser.getId());
        }
    }
    
}
