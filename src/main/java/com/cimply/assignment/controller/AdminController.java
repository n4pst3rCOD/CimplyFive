package com.cimply.assignment.controller;

import com.cimply.assignment.model.Address;

import com.cimply.assignment.model.Customer;
import com.cimply.assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/cimply/admin")
public class AdminController {

    @Autowired
    CustomerService customerService;

    Customer customer = null;

    @GetMapping("/panel")
    public String showAdminOptions(){



        return "panel";
    }

    @PostMapping(value="/customer")
    public String findCustomerById(@RequestParam("id") String id, ModelMap model, @RequestParam("emailid") String emailId, @RequestParam("mobilenumber") String mobileNumber) {
        if(id != ""){

            int customerId = Integer.parseInt(id);
            customer = customerService.findById(customerId);

        }
        else
        if(emailId != ""){

            customer = customerService.findByEmailID(emailId.trim());
        }
        else
        if(mobileNumber != ""){

            customer = customerService.findByMobileNumber(mobileNumber.trim());

        }
        model.addAttribute("customer", customer);

        if(customer != null)
        return "adminpanel";
        else return "notfound";
    }

    @GetMapping(value="/customer/{id}")
    @ResponseBody
    public Customer findCustomerById(@PathVariable("id") Integer id) {
        Customer customer = customerService.findById(id);


        return customer;
    }

    @PutMapping(value="updatecustomer")
    @ResponseBody
    public String updateCustomerDetails(@ModelAttribute("add") ArrayList<Address> addresses, Customer customer){

        //customer.setAddresses(addresses);

       // List<Address> addressList = addresses.getAddressList();
       int id = customerService.updateCustomerObject(customer);

        return "panel";
    }

}
