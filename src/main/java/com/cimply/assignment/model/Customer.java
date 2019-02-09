package com.cimply.assignment.model;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
@Table(name="CUSTOMER_DETAILS")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @Column(name="FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name="MIDDLE_NAME" )
    private String middleName;

    @Column(name="LAST_NAME",  nullable = false)
    private String lastName;

    @Column(name = "EMAIL_ID",  nullable = false)
    private String emailID;

    @Column(name = "MOBILE_NUMBER",  nullable = false)
    private String mobileNumber;

    @Column(name = "OFFICE_NUMBER")
    private String officeNumber;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList<>();

    @Column(name = "DIGEST_FREQUENCY",  nullable = false)
    private String digestFrequency;



    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getDigestFrequency() {
        return digestFrequency;
    }

    public void setDigestFrequency(String digestFrequency) {
        this.digestFrequency = digestFrequency;
    }
}
