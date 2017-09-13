package com.xjb.service;

import com.xjb.model.Course;
import com.xjb.utils.sqlHelper;

public class CourseService {
	
	private static final String SELECTONE="insert into SIMCourseSchedule(weekno,week,section,courseName,classes,num,teacher,address,courseType)  values(?,?,?,?,?,?,?,?,?)";
	public Boolean InsertCourse(Course course)
	{
		String[] parameters={course.getWeekno(),course.getWeek(),course.getSection(),course.getCourseName(),
				course.getClasses(),course.getNum(),course.getTeacher(),course.getAddress(),course.getCourseType()};
		boolean b= sqlHelper.excuteUpdate(SELECTONE, parameters);
		
		return b;
		
	}
}
