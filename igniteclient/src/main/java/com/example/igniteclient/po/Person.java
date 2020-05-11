package com.example.igniteclient.po;


import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.concurrent.atomic.AtomicInteger;

public class Person {

    private static final AtomicInteger ID_GEN = new AtomicInteger();

    /** Person ID (indexed) */
    @QuerySqlField(index = true)
    public Integer id;

    /** Person name(not-indexed) */
    @QuerySqlField
    public String name;

    /** Person phone(not-indexed) */
    @QuerySqlField
    public String phone;

    public Person(){

    }

    /**
     * Constructs Person record
     * @param id Person ID
     * @param name Person name
     * @param phone Person phone
     */
    public Person(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Constructs Person record
     * @param name Person name.
     * @param phone Person phone.
     */
    public Person(String name, String phone) {
        // Generate unique ID for this person.
        this.id = ID_GEN.incrementAndGet();

        this.name = name;
        this.phone = phone;
    }

    /**
     * Get method
     * @return Person ID.
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "Person [id=" + id +
                ", name=" + name +
                ", phone=" + phone + "]";
    }
}