package com.xjb.model;
/**
 * ����json�������͵�Course
 * @author xjb
 *
 */

//xqj�������ڼ��ϿΣ�skjc����ڼ��ڣ�skcd��ʾ�Ͽγ��ȣ���2��������2�ڣ�kcmc����������ݡ�
public class CourseJson {
	private String xqj;  //���ڼ��Ͽ�
	private String skjc;	//�ڼ���
	private String skcd;	//�Ͽγ��ȣ�
	private String kcmc;	//�������ݡ� �γ�����+�Ͽεص�
	private String courseName;//����
	private String address;		//����
	private String teacher;	//�ڿ���ʦ
	private String classes;	//�༶
	private String weekno;	//�ڼ���
	
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
