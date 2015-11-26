package com.asiainfo.hanyx.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.hanyx.dao.IPersonDao;

public class PersonSrvImpl implements IPersonSrv{
	@Autowired
	private IPersonDao personDao;
	
}
