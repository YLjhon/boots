package com.example.igniteclient.dao.ignite;

import com.example.igniteclient.po.Person;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IgnitePersonRepository {

    @Autowired
    private IgniteCache<Integer, Person> igniteCache;

    public Person findById(Integer id){
        Person person=igniteCache.get(id);
        return person;
    }

    public void add(Person person){
        igniteCache.put(person.getId(),person);
    }

    public List<Person> findByName(String name){
        List<Person> list=new ArrayList<>();
        SqlFieldsQuery sql1 = new SqlFieldsQuery("select id,name,phone from Person where name=?");
        sql1.setArgs(name);
        QueryCursor<List<?>> cursor1 = igniteCache.query(sql1);
        for (List<?> row : cursor1)
        {
            Person person=new Person();
            person.setId(Integer.parseInt(String.valueOf(row.get(0))));
            person.setName(String.valueOf(row.get(1)));
            person.setPhone(String.valueOf(row.get(2)));
            list.add(person);
        }
        return list;
    }
}
