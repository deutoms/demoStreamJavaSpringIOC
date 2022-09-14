package com.project.dedi.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "user_transaction")
public class TransactionRecord {

    @Id
    @Column(name = "id_transaction")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "transaction_name")
    private String transactionName;

    @Column(name = "total_item")
    private int totalItem;


    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_id")
    private Customer customer_id;

    public void setId(Long id) {
        this.id = id;
    }

   public Long getId() {
        return id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }



    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }
}
