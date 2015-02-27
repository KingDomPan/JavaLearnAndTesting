package com.panqd.hibernate.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Customer implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private int phone;
    private String address;
    private char sex;
    private boolean married;
    private String description;
    private byte[] image;
    private Date birthday;
    private Timestamp registeredTime;
    private Set<Order> orders = new HashSet<Order>();
    
    public Customer() {
        
    }
    
    public Customer(String name) {
        super();
        this.name = name;
    }
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPhone() {
        return this.phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public char getSex() {
        return this.sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public boolean isMarried() {
        return this.married;
    }
    public void setMarried(boolean married) {
        this.married = married;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public byte[] getImage() {
        return this.image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Timestamp getRegisteredTime() {
        return this.registeredTime;
    }
    public void setRegisteredTime(Timestamp registeredTime) {
        this.registeredTime = registeredTime;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
}