package com.asiainfo.hanyx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.hanyx.bean.Person;
import com.asiainfo.hanyx.dao.IPersonDao;

@Controller
public class PersonController {
	@Autowired
	private IPersonDao personDao;

	@RequestMapping(value = "/main.do")  
	public ModelAndView goHomePage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("personList", personDao.findAll());
		mv.setViewName("main");
		return mv;
    }
    
	@RequestMapping(value = "/insert.ajax")  
	public void insert(HttpServletRequest
    		request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("personName");
		String pwd = request.getParameter("personPwd");
		
		Person p = new Person();
		p.setName(name);
		p.setPwd(pwd);
		
		personDao.save(p);
		
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", true);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(result).toString());
		out.close();
    }
	
	@RequestMapping(value = "/delete.ajax")  
	public void delete(HttpServletRequest
    		request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		int res = personDao.deleteById(id);
		
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", res==1);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(result).toString());
		out.close();
    }
	
	@RequestMapping(value = "/update.ajax")  
	public void update(HttpServletRequest
    		request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("editId");
		String name = request.getParameter("editName");
		String pwd = request.getParameter("editPwd");
		
		Person p = new Person();
		p.setId(id);
		p.setName(name);
		p.setPwd(pwd);
		
		int res = personDao.updateFirst(p);
		
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", res==1);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(result).toString());
		out.close();
    }
    
}
