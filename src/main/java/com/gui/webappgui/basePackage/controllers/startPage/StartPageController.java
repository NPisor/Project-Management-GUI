package com.gui.webappgui.basePackage.controllers.startPage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/userSearch", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String customerSearch(String id) throws Exception
    {
        Optional<Users> user;
        user = usersRepo.findById(Long.valueOf(id));
        if(user.isEmpty()){
            throw new Exception("No customer found with ID: " + id);
        }
        else{
            ObjectMapper om = new ObjectMapper(); 
            return om.writeValueAsString(user.get());
        }        
    }

}

