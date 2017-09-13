package com.xjb.action;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.xjb.model.CourseJson;
import com.xjb.service.CourseService;



public class CourseAction extends ActionSupport {
	private Map<String, Object> dataMap;   
 
	public Map<String, Object> getDataMap() {   
        return dataMap;   
	}
	
	private String weekno;
	private String classes;
	private String teacher;
	private CourseService courseService;
	
	
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}



	public void setWeekno(String weekno) {
		this.weekno = weekno;
	}



	public void setClasses(String classes) {
		this.classes = classes;
	}



	//ͨ���༶��ѯ
	public String findbyWeeknoAndClass() throws Exception{
		 dataMap = new HashMap<String, Object>();
	    dataMap.clear();	
	    courseService=new CourseService();
	    weekno="��"+weekno+"��";
	    
	    List<CourseJson> courseJsons= courseService.selectbyClasses(classes, weekno);
	    dataMap.put("courseJsons", courseJsons); 
	    dataMap.put("success", true);  
		return "findbyWeeknoAndClass";
		
		}
	
	//ͨ����ʦ��ѯ
	public String findbyWeeknoAndTeacher() throws Exception{
		 dataMap = new HashMap<String, Object>();
	    dataMap.clear();	
	    courseService=new CourseService();
	    weekno="��"+weekno+"��";
	   // System.out.println(teacher);
	    List<CourseJson> courseJsons= courseService.selectbyTeacher(teacher, weekno);
	    dataMap.put("courseJsons", courseJsons); 
	    dataMap.put("success", true);  
		return "findbyWeeknoAndTeacher";
		
		}
	
	public String findAllTeacher() throws Exception{
		 dataMap = new HashMap<String, Object>();
	    dataMap.clear();	
	    courseService=new CourseService();
	  
	   // System.out.println(teacher);
	    List<String> teachers= courseService.findAllTeacher();
	    dataMap.put("teachers", teachers); 
	    dataMap.put("success", true);  
		return "findAllTeacher";
	}
	
}
