package com.xjb.model;

public class Course {
	private int id;
	private String weekno; //�ܴ�
	private String week;  //����
	private String section; //�ڴ�
	private String courseName; //�γ�����
	private String classes;  //�༶
	private String num;	//����
	private String teacher;		//��ʦ
	private String address;		//����
	private String courseType;		//�γ�����
	
	
	
	public Course() {
		super();
	}



	public Course(int id, String weekno, String week, String section,
			String courseName, String classes, String num, String teacher,
			String address, String courseType) {
		super();
		this.id = id;
		this.weekno = weekno;
		this.week = week;
		this.section = section;
		this.courseName = courseName;
		this.classes = classes;
		this.num = num;
		this.teacher = teacher;
		this.address = address;
		this.courseType = courseType;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getWeekno() {
		return weekno;
	}



	public void setWeekno(String weekno) {
		this.weekno = weekno;
	}



	public String getWeek() {
		return week;
	}



	public void setWeek(String week) {
		this.week = week;
	}



	public String getSection() {
		return section;
	}



	public void setSection(String section) {
		this.section = section;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getClasses() {
		return classes;
	}



	public void setClasses(String classes) {
		this.classes = classes;
	}



	public String getNum() {
		return num;
	}



	public void setNum(String num) {
		this.num = num;
	}



	public String getTeacher() {
		return teacher;
	}



	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCourseType() {
		return courseType;
	}



	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}



	@Override
	public String toString() {
		return "Course [id=" + id + ", weekno=" + weekno + ", week=" + week
				+ ", section=" + section + ", courseName=" + courseName
				+ ", classes=" + classes + ", num=" + num + ", teacher="
				+ teacher + ", address=" + address + ", courseType="
				+ courseType + "]";
	}
	
	
	
	
	
}
