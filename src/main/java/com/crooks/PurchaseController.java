/*
 * Copyright (c) 2016.
 */

package com.crooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;


/**
 * Created by johncrooks on 6/22/16.
 */
@Controller
public class PurchaseController {

    @Autowired
    PurchaseRepository purchases;
    @Autowired
    CustomerRepository customers;

    @PostConstruct
    void init() throws FileNotFoundException {
        File f = new File("customers.csv");
        File x = new File("purchases.csv");
        Scanner scanner = new Scanner(f);
        Scanner scanner2 = new Scanner(x);
        scanner.nextLine();
        if (customers.count()==0) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);
            }
        }
        scanner2.nextLine();
        if (purchases.count()==0) {
            while (scanner2.hasNext()) {
                String line = scanner2.nextLine();
                String[] columns = line.split(",");
                Purchase purchase = new Purchase(columns[1], columns[2],columns[3],columns[4],customers.findOne(Integer.valueOf(columns[0])));
                purchases.save(purchase);
            }
        }
    }

    @RequestMapping(path="/", method= RequestMethod.GET)
    public String home(HttpSession session, Model model, String category, String nameSearch){
        Iterable<Purchase> p=null;

        Iterable<Customer> c=null;

        if (nameSearch !=null){
            c= customers.findByNameContainingIgnoreCase(nameSearch);

        } else if (nameSearch==null && category!=null){
                p = purchases.findByCategory(category);
            }else if (nameSearch==null && category==null){
                p = purchases.findAll();
            }else{
            }




        model.addAttribute("purchases", p);
        model.addAttribute("customers", c);
        return "home";
    }
}
