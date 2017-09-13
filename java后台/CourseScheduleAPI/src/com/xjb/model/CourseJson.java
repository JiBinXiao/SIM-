package com.xjb.model;
/**
 * 返回json数据类型的Course
 * @author xjb
 *
 */

//xqj代表星期几上课，skjc代表第几节，skcd表示上课长度，如2代表连上2节，kcmc代表具体内容。
public class CourseJson {
	private String xqj;  //星期几上课
	private String skjc;	//第几节
	private String skcd;	//上课长度，
	private String kcmc;	//具体内容。 课程名称+上课地点
	private String courseName;//名称
	private String address;		//机房
	private String teacher;	//授课老师
	private String classes;	//班级
	private String weekno;	//第几周
	
	public CourseJson() {
		super();
	}

	


	
	public CourseJson(String xqj, String skjc, String skcd, String kcmc,
			String courseName, String address, String teacher, String classes,
			String weekno) {
		super();
		this.xqj = xqj;
		this.skjc = skjc;
		this.skcd = skcd;
		this.kcmc = kcmc;
		this.courseName = courseName;
		this.address = address;
		this.teacher = teacher;
		this.classes = classes;
		this.weekno = weekno;
	}





	public String getCourseName() {
		return courseName;
	}





	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getXqj() {
		return xqj;
	}
	public void setXqj(String xqj) {
		this.xqj = xqj;
	}
	public String getSkjc() {
		return skjc;
	}
	public void setSkjc(String skjc) {
		this.skjc = skjc;
	}
	public String getSkcd() {
		return skcd;
	}
	public void setSkcd(String skcd) {
		this.skcd = skcd;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getWeekno() {
		return weekno;
	}
	public void setWeekno(String weekno) {
		this.weekno = weekno;
	}
	@Override
	public String toString() {
		return "CourseJson [xqj=" + xqj + ", skjc=" + skjc + ", skcd=" + skcd
				+ ", kcmc=" + kcmc + ", teacher=" + teacher + ", classes="
				+ classes + ", weekno=" + weekno + "]";
	}

	
	
	
}
