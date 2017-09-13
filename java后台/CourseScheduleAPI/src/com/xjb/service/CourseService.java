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
	
	//ͨ���༶��ѯ
	public List<CourseJson> selectbyClasses(String classes,String weekno)
	{
		List<Course> courses= courseDao.selectbyClasses(classes,weekno);
		
		
		return CourseToCourseJson(courses);
	}
	
	//ͨ����ʦ��ѯ
	public List<CourseJson> selectbyTeacher(String teacher,String weekno)
	{
		List<Course> courses= courseDao.selectbyTeacher(teacher,weekno);
		
		
		return CourseToCourseJson(courses);
	}
	
	//����������ʦ
	public List<String> findAllTeacher() {
		// TODO Auto-generated method stub
		List<String> teachers= courseDao.findAllTeacher();
		
		
		return teachers;
	}
	
	//��������������ת����һ��json����
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
				case "һ":
					tempweek="1";
					break;
				case "��":
					tempweek="2";
					break;
				case "��":
					tempweek="3";
					break;
				case "��":
					tempweek="4";
					break;
				case "��":
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
				//�Ͽν���Ϊ5 �����µ�����
				if(sectionNum ==5)
					b=false;
				//����������һ�����ݲ���ͬ
				if(!b || !tempweekno.equals(weekno) || !tempweek.equals(week)
						|| !tempcourseName.equals(courseName) || !tempclasses.equals(classes) 
						|| !tempnum.equals(num) && !tempteacher.equals(teacher) || !tempaddress.equals(address)
						|| !tempcourseType.equals(courseType))
				{
					if(courseJson!=null)
						courseJsons.add(courseJson);
					//ȡ���Ͽ�ʱ��
					startsection=Integer.parseInt(tempsection);
					courseJson=new CourseJson();
					//���ڼ�
					
					courseJson.setXqj(tempweek);
					//�ڼ���
					courseJson.setSkjc(startsection+"");
					//kcmc ��������
					courseJson.setKcmc(tempcourseName+"@"+tempaddress);
					courseJson.setCourseName(tempcourseName);
					courseJson.setAddress(tempaddress);
					//��ʦ
					courseJson.setTeacher(tempteacher);
					//�༶
					courseJson.setClasses(tempclasses);
					//�ڼ���
					courseJson.setWeekno(tempweekno);
					//�����µĶ���
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
				//����������һ��������ͬ
				else
				{
					
					//skcd����
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
