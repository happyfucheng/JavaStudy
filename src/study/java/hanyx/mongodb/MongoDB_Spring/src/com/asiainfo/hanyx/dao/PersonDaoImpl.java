package com.asiainfo.hanyx.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.asiainfo.hanyx.bean.Person;

public class PersonDaoImpl implements IPersonDao{
	@Autowired
	private MongoTemplate mongoTemplate;

	private Query getQuery(String key, String value) {
		return new Query(Criteria.where(key).is(value));
	}
	
	private Query getQuery(Person p) {
		Query q = new Query();
		if (p.getId() != null) {
			q.addCriteria(Criteria.where("id").is(p.getId()));
		}
		if (p.getName() != null) {
			q.addCriteria(Criteria.where("name").is(p.getName()));
		}
		if (p.getPwd() != null) {
			q.addCriteria(Criteria.where("pwd").is(p.getPwd()));
		}
		return q;
	}
	
	private Update getUpdate(Person p) {
		Update u = new Update();
		if (p.getName() != null) {
			u.set("name", p.getName());
		}
		if (p.getPwd() != null) {
			u.set("pwd", p.getPwd());
		}
		return u;
	}
	
	@Override
	public Person findById(String id) {
		return mongoTemplate.findOne(getQuery("id", id), Person.class/*, PERSON_COLLECTIONS*/);
	}
	
	@Override
	public List<Person> findByCondition(Person p) {
		return mongoTemplate.find(getQuery(p), Person.class);
	}

	@Override
	public List<Person> findAll() {
		return mongoTemplate.findAll(Person.class);
	}
	
	@Override
	public void save(Person person) {
		mongoTemplate.save(person/*, PERSON_COLLECTIONS*/);
	}

	@Override
	public int deleteById(String id) {
		return mongoTemplate.remove(getQuery("id", id), Person.class).getN();
	}

	@Override
	public int deleteByName(String name) {
		return mongoTemplate.remove(getQuery("name", name)).getN();
	}

	@Override
	public int updateFirst(Person p) {
		return mongoTemplate.updateFirst(getQuery("id", p.getId()), getUpdate(p), Person.class).getN();
	}

	@Override
	public int updateMulti(Person p) {
		return mongoTemplate.updateMulti(getQuery("id", p.getId()), getUpdate(p), Person.class).getN();
	}

	@Override
	public int updateInsert(Person p) {
		return mongoTemplate.upsert(getQuery("id", p.getId()), getUpdate(p), Person.class).getN();
	}
}
