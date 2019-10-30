package kr.ac.green;

public class Data {
	private String id;
	private String pw;
	private String name;
	private String nick;
	private String gender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Data(String id, String pw, String name, String nick, String gender) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nick = nick;
		this.gender = gender;
	}
	
	@Override
	public String toString(){
		String info = "Id : " + id + ", Pw : " + pw + ", Name : " + name  + ", Nick : " + nick + ", gender : " + gender;
		return info;
	}

	
}
