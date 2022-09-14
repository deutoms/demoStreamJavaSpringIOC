package com.project.dedi.demo.controller;


import com.project.dedi.demo.model.Customer;
import com.project.dedi.demo.model.TransactionRecord;
import com.project.dedi.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CustomerController {


    @Autowired
    private CustomerRepository customersRepo;

    @GetMapping("/customer/{id}")
    private Customer getCustomerById(@PathVariable("id") String id){
       Customer custData = customersRepo.getCustomers(id);
        return custData;
    }

    @GetMapping("/customerAdd")
    private Customer addCustomer(@RequestParam("name") String name, @RequestParam("address")String address,@RequestParam("userId")String userId,@RequestParam("dob")String birth){
        Customer custData = new Customer();
        custData.setName(name);
        custData.setAddress(address);
        custData.setUser_id(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date birthdate;
        try{
            birthdate =  sdf.parse(birth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        custData.setDob(birthdate);
        customersRepo.save(custData);
        return custData;
    }

    @GetMapping("/customerEdit/{id}/{name}/{address}")
    private Customer updateCustomer(@PathVariable("id") String id,@PathVariable("name") String name, @PathVariable("address")String address){
        Customer custData = customersRepo.getCustomers(id);
        custData.setName(name);
        custData.setAddress(address);
        customersRepo.save(custData);
        return custData;
    }

    @GetMapping("/customerDelete/{id}")
    private String deleteCust(@PathVariable("id") String id){
        Customer custData = customersRepo.getCustomers(id);
        customersRepo.delete(custData);
        return "successDelete";
    }

    @GetMapping("/customerSortByNameAsc")
    private String sortNameAsc(){
        List<String> listCust = customersRepo.getCustomerSortByName();

        List<String> newSortedAsc = listCust.stream().sorted().collect(Collectors.toList());
        List<String> newSortedDesc = listCust.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return newSortedAsc.toString();
    }

    @GetMapping("/customerSortByNameDesc")
    private String sortNameDesc(){
        List<String> listCust = customersRepo.getCustomerSortByName();

        List<String> newSortedDesc = listCust.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return newSortedDesc.toString();
    }

    @GetMapping("/filterNameFront")
    private String filterByStartCharacter(@RequestParam("filter") String startChar){
        List<String> listCust = customersRepo.getCustomerSortByName();

        List<String> newFilter = listCust.stream().filter(s -> s.startsWith(startChar)).collect(Collectors.toList());

        return newFilter.toString();
    }

    @GetMapping("/getTransactionByUserId")
    private String getCustomerTransaction(@RequestParam("id") String id){
        Customer custData = customersRepo.getCustomers(id);
        List<TransactionRecord> trxRecord= custData.getRecordList();
        List print = new ArrayList();
        for(TransactionRecord x :trxRecord){
            print.add(x.getTransactionName());
        }


        return print.toString();
    }
}
