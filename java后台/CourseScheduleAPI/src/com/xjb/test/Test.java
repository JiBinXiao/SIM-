package com.xjb.test;

import java.util.List;

import com.xjb.model.CourseJson;
import com.xjb.service.CourseService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseService courseService=new CourseService();
	    List<CourseJson> courseJsons=	courseService.selectbyClasses("�Ź�1401", "��1��");
	    for (CourseJson courseJson : courseJsons) {
			System.out.println(courseJson);
		}
	}

}
