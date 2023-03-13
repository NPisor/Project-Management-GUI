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
import com.pmservice.basePackage.models.Task.Task;
import com.pmservice.basePackage.models.User.Users;
import com.pmservice.basePackage.repos.TaskRepo;
import com.pmservice.basePackage.repos.UsersRepo;

@Controller
public class CustomerPageController {

    @Autowired
    private TaskRepo taskRepo;
    
    @Autowired
    private UsersRepo usersRepo;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/tasksearch", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getTaskSearchResult(String id, Task task) throws JsonProcessingException
    {
        task= new Task();
        task = taskRepo.findById(Long.valueOf(id));
        ObjectMapper om = new ObjectMapper();

        return om.writeValueAsString(task);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/loadCustomer", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String loadCustomerData(String customerId, Users customer) throws JsonProcessingException
    {
        customer = new Users();
        customer = usersRepo.findById(Long.valueOf(customerId));
        ObjectMapper om = new ObjectMapper(); 

        return om.writeValueAsString(customer);
    }

    @GetMapping("/customer")
    public String loadCustomerPage(){
        return "customer";
    }
}
