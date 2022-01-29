package com.ebugtracker.admin.controller;

import com.ebugtracker.admin.entity.Bug;
import com.ebugtracker.admin.entity.Customer;
import com.ebugtracker.admin.entity.Staff;
import com.ebugtracker.admin.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
@RestController
public class AdminRestController {
    @Autowired
    private IStaffService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addStaff")
    public Staff addStaff(@RequestBody Staff requestData){
        return  service.addStaff(requestData);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/editStaff")
    public Staff editStaff(@RequestBody Staff requestData){
        return service.editStaff(requestData);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/deleteStaffById/{id}")
    public void deleteStaffById(@PathVariable Long id){
        service.deleteStaff(id);
    }
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/findStaffById/{id}")
    public Staff findStaffById(@PathVariable Long id){
        return service.findStaffById(id);
    }

    
    @GetMapping("/findAllStaff")
    public List<Staff> findAllStaff(){
        return service.findAll();
    }

    @GetMapping("/findAllCustomer")
    public List<Customer> findAllCustomer(){
        return service.findAllCustomers();
    }

    
    @GetMapping("/bugReport")
    public List<Bug> fetchBugReport(){
        return service.getBugReport();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/getReport/priority/{level}")
    public List<Bug> fetchBugReportByPriority(@PathVariable String level){
        return service.getBugReportByPriority(level);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/assign/{staffId}/{ticketId}")
    public Staff assignBugToStaff(@PathVariable Long staffId,@PathVariable Long ticketId){
        return service.assignBugToStaff(staffId,ticketId);
    }

    
    @PutMapping("/sendMessage/{customerId}")
    public Customer sendMessageToCustomer(@PathVariable Long customerId,@RequestBody String msg){
        return service.sendMessageToCustomer(customerId,msg);
    }

}
