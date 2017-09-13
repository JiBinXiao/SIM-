package com.xjb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xjb.model.Course;
import com.xjb.model.CourseJson;
import com.xjb.utils.sqlHelper;

public class CourseDao {
	
	private static final String SELECTBYCLASSES="select  * from [dbo].[SIMCourseSchedule] where weekno=? and classes like ?";
	
	private static final String SELECTBYTEACHER="select  * from [dbo].[SIMCourseSchedule] where weekno=? and teacher like ?";
	
	private static final String SELECTALLTEACHER="select  distinct teacher from [dbo].[SIMCourseSchedule] order by teacher";

	
	//通过班级和周数查询
	public List<Course> selectbyClasses(String classes, String weekno) {
		// TODO Auto-generated method stub
		String[] parameters={weekno,"%"+classes+"%"};
		ResultSet rs=  sqlHelper.excuteQuery(SELECTBYCLASSES, parameters);

		return 	RsToCourseList(rs);
	}
	
	//通过教师和周数查询
	public List<Course> selectbyTeacher(String teacher,String weekno)
	{
		String[] parameters={weekno,"%"+teacher+"%"};
		System.out.println(teacher);
		ResultSet rs=  sqlHelper.excuteQuery(SELECTBYTEACHER, parameters);

		return 	RsToCourseList(rs);
	}
	
	//查找所有老师
	public List<String> findAllTeacher() {
		// TODO Auto-generated method stub
		List<String> teachers=new ArrayList<String>();
		ResultSet rs=sqlHelper.excuteQuery(SELECTALLTEACHER,null);
		try {
			while(rs.next())
			{
				String teacher= rs.getString(1);
				System.out.println(teacher);
				teachers.add(teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(teachers!=null && teachers.size()>0)
			return teachers;
		else
			return null;
	}


	
	
	//将rs封装成课程集合对象
	public List<Course> RsToCourseList(ResultSet rs)
	{
		 List<Course> courses=new ArrayList<Course>();
		
		try {
			while(rs.next())
			{
				Course course=new Course();
				course.setId(rs.getInt(1));
				course.setWeekno(rs.getString(2));
				course.setWeek(rs.getString(3));
				course.setSection(rs.getString(4));
				course.setCourseName(rs.getString(5));
				course.setClasses(rs.getString(6));
				course.setNum(rs.getString(7));
				course.setTeacher(rs.getString(8));
				course.setAddress(rs.getString(9));
				course.setCourseType(rs.getString(10));
				courses.add(course);
				//System.out.println(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
			sqlHelper.closesqlHelper(sqlHelper.getRs(), sqlHelper.getPs(), sqlHelper.getCt());
		}
		
		if(courses!=null && courses.size()>0)
			return courses;
		else
			return null;
	}



}
