package com.gui.webappgui.basePackage.controllers.startPage;


import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pmservice.basePackage.models.Task.Task;
import com.pmservice.basePackage.models.User.Users;
import com.pmservice.basePackage.repos.TaskRepo;
import com.pmservice.basePackage.repos.UsersRepo;

@Controller
@CrossOrigin(origins = "*")
public class CustomerPageController {

    @Autowired
    private TaskRepo taskRepo;
    
    @Autowired
    private UsersRepo usersRepo;

    
    @RequestMapping(value = "/tasksearch", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getTaskSearchResult(String id) throws Exception
    {
        Optional<Task> task;
        task = taskRepo.findById(Long.valueOf(id));
        if(task.isEmpty()){
            throw new Exception("No task has been found with ID: " + id);
        }
        else{
            OffsetDateTime created = OffsetDateTime.ofInstant(Instant.ofEpochMilli(task.get().getCreatedTs().getTime()), ZoneId.systemDefault());
            OffsetDateTime complete = OffsetDateTime.ofInstant(Instant.ofEpochMilli(task.get().getTaskCompleted().getTime()), ZoneId.systemDefault());
            OffsetDateTime submitted = OffsetDateTime.ofInstant(Instant.ofEpochMilli(task.get().getTaskSubmittedForReview().getTime()), ZoneId.systemDefault());
            Users assigner = usersRepo.findById(task.get().getAssignerId()).get();
            ObjectMapper om = new ObjectMapper();  
            JsonNode jsonNode = om.readTree(om.writeValueAsString(task.get()));
            ((ObjectNode)jsonNode).put("assignerName", assigner.getFName() + " " + assigner.getLName());
            ((ObjectNode)jsonNode).put("createdDt", created.toLocalDateTime().toString().replace('T', ' '));
            ((ObjectNode)jsonNode).put("completedDt", complete.toLocalDateTime().toString().replace('T', ' '));
            ((ObjectNode)jsonNode).put("submittedDt", submitted.toLocalDateTime().toString().replace('T', ' '));
            return om.writeValueAsString(jsonNode);
        }

        
    }

    @RequestMapping(value = "/loadCustomer", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String loadCustomerData(String customerId) throws Exception
    {
        Optional<Users> customer;
        customer = usersRepo.findById(Long.valueOf(customerId));
        if(customer.isEmpty()){
            throw new Exception("No customer found with ID: " + customerId);
        }
        else{
            ObjectMapper om = new ObjectMapper(); 
            return om.writeValueAsString(customer.get());
        }        
    }

    @GetMapping("/customer")
    public String loadCustomerPage(){
        return "customer";
    }
}
