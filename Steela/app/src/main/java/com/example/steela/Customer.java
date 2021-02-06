package com.example.steela;

public class Customer {
    public String customer_name;
    public String customer_address;
    public String customer_tel;
    public String customer_detail;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_tel() {
        return customer_tel;
    }

    public void setCustomer_tel(String customer_tel) {
        this.customer_tel = customer_tel;
    }

    public String getCustomer_detail() {
        return customer_detail;
    }

    public void setCustomer_detail(String customer_detail) {
        this.customer_detail = customer_detail;
    }

    public Customer(String customer_name, String customer_address, String customer_tel, String customer_detail) {
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_tel = customer_tel;
        this.customer_detail = customer_detail;

    }





}
