package com.xjb.service;

import java.util.ArrayList;
import java.util.List;

import com.xjb.dao.CourseDao;
import com.xjb.model.*;
public class CourseService {
	
	private CourseDao courseDao;
	
	public CourseService()
	{
		super();
		courseDao=new CourseDao();
	}
	
	//通过班级查询
	public List<CourseJson> selectbyClasses(String classes,String weekno)
	{
		List<Course> courses= courseDao.selectbyClasses(classes,weekno);
		
		
		return CourseToCourseJson(courses);
	}
	
	//通过教师查询
	public List<CourseJson> selectbyTeacher(String teacher,String weekno)
	{
		List<Course> courses= courseDao.selectbyTeacher(teacher,weekno);
		
		
		return CourseToCourseJson(courses);
	}
	
	//查找所有老师
	public List<String> findAllTeacher() {
		// TODO Auto-generated method stub
		List<String> teachers= courseDao.findAllTeacher();
		
		
		return teachers;
	}
	
	//将多条相似数据转换成一条json数据
	public List<CourseJson> CourseToCourseJson(List<Course> courses)
	{
		List<CourseJson> courseJsons=new ArrayList<CourseJson>();
		CourseJson courseJson=null;
		String weekno="";
		String week="";
		int startsection=0;
		int sectionNum=0;
		String courseName="";
		String classes="";
		String num="";
		String teacher="";
		String address="";
		String courseType="";
		if(courses!=null && courses.size()>0)
		{
			for(int i=0;i<courses.size();i++)
			{
				Boolean b=true;
				String tempweekno=courses.get(i).getWeekno();
				String tempweek=courses.get(i).getWeek().substring(2);
				switch (tempweek) {
				case "一":
					tempweek="1";
					break;
				case "二":
					tempweek="2";
					break;
				case "三":
					tempweek="3";
					break;
				case "四":
					tempweek="4";
					break;
				case "五":
					tempweek="5";
					break;
				default:
					break;
				}
				String tempsection=courses.get(i).getSection();
				if(tempsection.length()==3)
					tempsection=tempsection.substring(1, 2);;
				if(tempsection.length()==4)
					tempsection=tempsection.substring(1, 3);;
				//String tempsection=courses.get(i).getSection().substring(1, 2);
				String tempcourseName=courses.get(i).getCourseName();
				String tempclasses=courses.get(i).getClasses();
				String tempnum=courses.get(i).getNum();
				String tempteacher=courses.get(i).getTeacher();
				String tempaddress=courses.get(i).getAddress();
				String tempcourseType=courses.get(i).getCourseType();
				sectionNum++;
				//上课节数为5 则另创新的数据
				if(sectionNum ==5)
					b=false;
				//此数据与上一条数据不相同
				if(!b || !tempweekno.equals(weekno) || !tempweek.equals(week)
						|| !tempcourseName.equals(courseName) || !tempclasses.equals(classes) 
						|| !tempnum.equals(num) && !tempteacher.equals(teacher) || !tempaddress.equals(address)
						|| !tempcourseType.equals(courseType))
				{
					if(courseJson!=null)
						courseJsons.add(courseJson);
					//取出上课时间
					startsection=Integer.parseInt(tempsection);
					courseJson=new CourseJson();
					//星期几
					
					courseJson.setXqj(tempweek);
					//第几节
					courseJson.setSkjc(startsection+"");
					//kcmc 具体内容
					courseJson.setKcmc(tempcourseName+"@"+tempaddress);
					courseJson.setCourseName(tempcourseName);
					courseJson.setAddress(tempaddress);
					//老师
					courseJson.setTeacher(tempteacher);
					//班级
					courseJson.setClasses(tempclasses);
					//第几周
					courseJson.setWeekno(tempweekno);
					//创建新的对象
					weekno=tempweekno;
					week=tempweek;
					courseName=tempcourseName;
					classes=tempclasses;
					num=tempnum;
					teacher=tempteacher;
					address=tempaddress;
					courseType=tempcourseType;
					sectionNum=1;
				}
				//此数据与上一条数据相同
				else
				{
					
					//skcd长度
					courseJson.setSkcd(sectionNum+"");
					if(i==courses.size()-1)
					{
						if(courseJson!=null)
							courseJsons.add(courseJson);
					}
					
				}

			}
		}
		
		
		return courseJsons;
	}

	
}
