package com.example.igniteclient.controller;

import com.example.igniteclient.po.Person;
import com.example.igniteclient.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Save a person with name and phone sent by front end.
     * @param name Person name.
     * @param phone Person phone.
     * @return The person saved in ignite DB
     */
    @RequestMapping("/add")
    public Person savePerson(@RequestParam(value = "id") Integer id,@RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone){
        Person person=new Person(id,name, phone);
        personService.save(person);
        return person;
    }

    /**
     * Find a person with given name sent by front end.
     * @return The person found in ignite DB
     */
    @RequestMapping("/findById")
    public Person findById(@RequestParam(value = "id") Integer id){
        return personService.findById(id);
    }

    @RequestMapping("/findByName")
    public List<Person> findByName(@RequestParam(value = "name") String name){
        return personService.findByName(name);
    }

}
