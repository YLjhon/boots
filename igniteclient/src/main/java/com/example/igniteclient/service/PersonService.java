package com.example.igniteclient.service;

import com.example.igniteclient.dao.ignite.IgnitePersonRepository;
import com.example.igniteclient.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private IgnitePersonRepository personRepository;

    /**
     * Save Person to Ignite DB
     * @param person Person object.
     * @return The Person object saved in Ignite DB.
     */
    public void save(Person person) {
        personRepository.add(person);
    }

    /**
     * Find a Person from Ignite DB with given name.
     * @return The person found in Ignite DB
     */
    public Person findById(Integer id){
        return personRepository.findById(id);
    }

    public List<Person> findByName(String name){
        return personRepository.findByName(name);
    }

}
