package com.panqd.hibernate.entity;

@SuppressWarnings("serial")
public class Order implements java.io.Serializable {

    private Long id;
    private String orderNumber;
    private Customer customer;

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Order(String orderNumber, Customer customer) {
        this.orderNumber = orderNumber;
        this.customer = customer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
