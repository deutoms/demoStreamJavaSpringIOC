package com.project.dedi.demo.repository;


import com.project.dedi.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select cust from Customer cust where cust.user_id = ?1")
    Customer getCustomers(String user_id);

    @Query("select cust.name from Customer cust ")
    List<String> getCustomerSortByName();


}
