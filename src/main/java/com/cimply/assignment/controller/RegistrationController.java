package com.cimply.assignment.controller;


import com.cimply.assignment.model.Customer;
import com.cimply.assignment.service.CustomerService;
import com.cimply.assignment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("cimply")

public class RegistrationController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmailService emailService;

    Customer customer = null;

    @GetMapping("register")
    public String showRegistrationForm(){

        return "form";
    }


    @PostMapping("addcustomer")
    public String addCustomerFromForm(HttpServletRequest request, ModelMap modelMap){

        modelMap.addAttribute("entityValue","");
         customer = customerService.createCustomerObject(request);
         if(!customerService.countByEmailId(customer.getEmailID())){
            modelMap.addAttribute("entityValue","Email already exists.");
            return "form";
         }

         if(!customerService.countByMobileNumber(customer.getMobileNumber())){
            modelMap.addAttribute("entityValue","Mobile number already exists.");
            return "form";
         }


        customerService.saveCustomerToDatabase(customer);
        emailService.sendEmail(customer.getEmailID(), customer.getFirstName(), customer.getLastName());
        modelMap.addAttribute("firstName", customer.getFirstName());
        modelMap.addAttribute("lastName", customer.getLastName());
        return "success";

    }
    @GetMapping(path="/api/add", produces = "application/json")
    @ResponseBody
    public List<Customer> getAllCustomers(){

       return customerService.findAll();
    }




}
