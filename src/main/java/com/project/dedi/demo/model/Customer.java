package com.project.dedi.demo.model;


import javax.persistence.*;
import java.nio.MappedByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "cust_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_ID")
    private String user_id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "DATEOFBIRTH")
    private Date dob;
    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(targetEntity = TransactionRecord.class, mappedBy = "customer_id", cascade = CascadeType.ALL)
    private List<TransactionRecord> recordList;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<TransactionRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<TransactionRecord> listRecord) {
        this.recordList = listRecord;
    }
}
