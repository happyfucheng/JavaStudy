package com.asiainfo.hanyx.dao;

import java.util.List;

import com.asiainfo.hanyx.bean.Person;

public interface IPersonDao {
	
	static final String PERSON_COLLECTIONS = "person";
	
	Person findById(String id);

	List<Person> findByCondition(Person p);
	
	List<Person> findAll();
	
	void save(Person person);
	
	int deleteById(String id);
	
	int deleteByName(String name);
	
	int updateFirst(Person p);
	
	int updateMulti(Person p);

	int updateInsert(Person p);
}
