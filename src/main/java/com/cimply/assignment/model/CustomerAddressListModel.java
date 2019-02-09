package com.cimply.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerAddressListModel {

    private List<Address> addressList = new ArrayList<>();

    public CustomerAddressListModel() {
    }

    public CustomerAddressListModel(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
