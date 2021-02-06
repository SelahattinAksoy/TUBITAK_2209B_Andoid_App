package com.example.steela;

public class Order_Class {
    public String product_name;
    public String product_date;
    public String product_amount;
    public String customer_name;
    public String customer_address;
    public String order_taker;
    public String delivery_date;
    public String delivery_situation;
    public Double latitude;
    public Double longitude;


    public Order_Class(String product_name, String product_date,
                       String product_amount, String customer_name, String customer_address, String order_taker,
                       String delivery_date, String delivery_situation,Double latitude,Double longitude) {
        this.product_name = product_name;
        this.product_date = product_date;
        this.product_amount = product_amount;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.order_taker = order_taker;
        this.delivery_date=delivery_date;
        this.delivery_situation=delivery_situation;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public  Order_Class(String product_name, String customer_name, String product_amount){
        this.product_name = product_name;
        this.customer_name = customer_name;
        this.product_amount = product_amount;

    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public String getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(String product_amount) {
        this.product_amount = product_amount;
    }

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

    public String getOrder_taker() {
        return order_taker;
    }

    public void setOrder_taker(String order_taker) {
        this.order_taker = order_taker;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_situation() {
        return delivery_situation;
    }

    public void setDelivery_situation(String delivery_situation) {
        this.delivery_situation = delivery_situation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
