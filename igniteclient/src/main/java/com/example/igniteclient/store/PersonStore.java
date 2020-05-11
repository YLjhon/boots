package com.example.igniteclient.store;

import com.example.igniteclient.dao.jdbc.PersonRepository;
import com.example.igniteclient.po.Person;
import com.example.igniteclient.utils.SpringContextUtil;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PersonStore implements CacheStore<Integer, Person> {

    private PersonRepository personRepository;


    @Override
    public void loadCache(IgniteBiInClosure<Integer, Person> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {
        List<Person> list=personRepository.findAll();
        for(Person p:list){
            igniteBiInClosure.apply(p.getId(),p);
        }
    }

    @Override
    public void sessionEnd(boolean b) throws CacheWriterException {

    }

    @Override
    public Person load(Integer key) throws CacheLoaderException {
        Person p=getPersonRepository().findById(key);
        return p;
    }

    @Override
    public Map<Integer, Person> loadAll(Iterable<? extends Integer> iterable) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Integer, ? extends Person> entry) throws CacheWriterException {
        getPersonRepository().insert(entry.getValue());
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Integer, ? extends Person>> collection) throws CacheWriterException {
        List<Person> list=new ArrayList<>();
        for(Cache.Entry<? extends Integer, ? extends Person> entry:collection){
            list.add(entry.getValue());
        }
        getPersonRepository().batchInsert(list);
    }

    @Override
    public void delete(Object o) throws CacheWriterException {

    }

    @Override
    public void deleteAll(Collection<?> collection) throws CacheWriterException {

    }

    private PersonRepository getPersonRepository(){
        if(personRepository==null){
            personRepository= (PersonRepository) SpringContextUtil.getBean("personRepository");
        }
        return personRepository;
    }
}
