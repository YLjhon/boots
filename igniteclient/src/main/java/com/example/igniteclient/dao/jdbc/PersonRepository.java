package com.example.igniteclient.dao.jdbc;

import com.example.igniteclient.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> findByName(String name){
        String sql="select * from person where name=?";
        Object[] params = new Object[] {name};

        return jdbcTemplate.query(sql, params, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setPhone(resultSet.getString("phone"));
                return person;
            }
        });
    }

    public Person findById(Integer id){
        String sql="select * from person where id=?";
        Object[] params = new Object[] {id};
        List<Person> list=jdbcTemplate.query(sql, params, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setPhone(resultSet.getString("phone"));
                return person;
            }
        });
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public List<Person> findAll(){
        String sql="select * from person";
        return jdbcTemplate.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setPhone(resultSet.getString("phone"));
                return person;
            }
        });
    }

    public void insert(Person person){
        Object[] params=new Object[] {person.getId(),person.getName(),person.getPhone()};
        String sql="insert into person(id,name,phone) values(?,?,?)";
        jdbcTemplate.update(sql,params);
    }

    public void batchInsert(List<Person> persons) {
        String sql = "INSERT INTO user(id,name, phone) VALUES(?, ?,?)";

        List<Object[]> params = new ArrayList<>();

        persons.forEach(item -> {
            params.add(new Object[] {item.getId(),item.getName(),item.getPhone()});
        });
        jdbcTemplate.batchUpdate(sql, params);
    }

}
