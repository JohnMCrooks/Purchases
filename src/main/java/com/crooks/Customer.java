/*
 * Copyright (c) 2016.
 */

package com.crooks;

import javax.persistence.*;
import java.util.List;

/**
 * Created by johncrooks on 6/22/16.
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    int id;

    String name;
    String email;

    @OneToMany(mappedBy = "customer")
    List<Purchase> purchaseList;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
