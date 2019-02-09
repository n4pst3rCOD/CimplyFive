package com.cimply.assignment.service;


import com.cimply.assignment.model.Address;
import com.cimply.assignment.model.Customer;
import com.cimply.assignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomerToDatabase(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(int id){
        Optional value =  customerRepository.findById(id);

        if(value.isPresent()){
            return (Customer) value.get();
        }

        else return null;
    }

    public Customer findByEmailID(String emailID){
        return customerRepository.findByEmailID(emailID);
    }

    public Customer findByMobileNumber(String mobileNumber){
        return customerRepository.findByMobileNumber(mobileNumber);
    }


    public int updateCustomerObject(Customer customer){
        customerRepository.save(customer);
        return customer.getCustomerID();
    }
    public Customer createCustomerObject(HttpServletRequest request){

        Customer customer = new Customer();

        customer.setFirstName(request.getParameter("firstname"));
        customer.setLastName(request.getParameter("lastname"));
        customer.setMiddleName(request.getParameter("middlename"));
        customer.setEmailID(request.getParameter("emailid"));
        customer.setMobileNumber(request.getParameter("mobilenumber"));
        customer.setOfficeNumber(request.getParameter("officenumber"));

        if(customer.getMiddleName()!= null && customer.getMiddleName().equalsIgnoreCase("")){
            customer.setMiddleName(null);
        }
        if(customer.getOfficeNumber()!=null &&customer.getOfficeNumber().equalsIgnoreCase("")){
            customer.setOfficeNumber(null);
        }

        customer.setDigestFrequency(request.getParameter("digest"));

        Address address1 = new Address();
        address1.setAddress(request.getParameter("address1"));
        address1.setLandmark(request.getParameter("landmark1"));
        address1.setPinCode(request.getParameter("pincode1"));
        address1.setCity(request.getParameter("city1"));
        address1.setState(request.getParameter("state1"));
        address1.setCountry(request.getParameter("country1"));
        address1.setCustomer(customer);

        if(address1.getLandmark() != null && address1.getLandmark().equalsIgnoreCase("")){
            address1.setLandmark(null);
        }

        Address address2 = new Address();
        address2.setAddress(request.getParameter("address2"));
        address2.setLandmark(request.getParameter("landmark2"));
        address2.setPinCode(request.getParameter("pincode2"));
        address2.setCity(request.getParameter("city2"));
        address2.setState(request.getParameter("state2"));
        address2.setCountry(request.getParameter("country2"));
        address2.setCustomer(customer);

        if(address2.getLandmark() != null && address2.getLandmark().equalsIgnoreCase("")){
            address2.setLandmark(null);
        }

        customer.getAddresses().add(address1);


        if(!(request.getParameter("address2") == ""))
            customer.getAddresses().add(address2);

        return customer;
    }

    public boolean countByEmailId(String emailID){

        if(customerRepository.countByEmailID(emailID) != 0){
            return false;
        }

        return true;
    }

    public boolean countByMobileNumber(String mobileNumber){

        if(customerRepository.countByMobileNumber(mobileNumber) != 0){
            return false;
        }
        return true;
    }



}
