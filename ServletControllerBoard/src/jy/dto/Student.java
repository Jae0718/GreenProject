package jy.dto;

public class Student {
	private int sid;
	private String sname;
	private String stel;
	private int sgrade;
	private String sclass;
	public Student(){}
	
	public Student(int sid, String sname, String stel, int sgrade, String sclass) {
		this.sid = sid;
		this.sname = sname;
		this.stel = stel;
		this.sgrade = sgrade;
		this.sclass = sclass;
	}

	
	public Student(String sname, String stel, int sgrade, String sclass) {
		this.sname = sname;
		this.stel = stel;
		this.sgrade = sgrade;
		this.sclass = sclass;
	}

	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getStel() {
		return stel;
	}
	public void setStel(String stel) {
		this.stel = stel;
	}
	public int getSgrade() {
		return sgrade;
	}
	public void setSgrade(int sgrade) {
		this.sgrade = sgrade;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", stel=" + stel + ", sgrade=" + sgrade + ", sclass="
				+ sclass + "]";
	}
	
	
}
