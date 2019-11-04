package jy.dao;

public interface IDaoConstants {
	String DRIVER = "com.mysql.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/test";
	String UID = "root";
	String UPW = "1234";
	
	int INSERT = 0;
	int GETALL = 1;
	int SELECT_BY_NAME = 2;
	int  SELECT_BY_TEL = 3;
	int SELECT_BY_GRADE = 4;
	int SELECT_BY_CLASS = 5;
	int SELECT_BY_ID = 6;
	int UPDATE = 7;
	int DELETE = 8;
	
//	int UPDATE = 2;
//	int DELETE =3;
//	 "UPDATE stu SET dtitle=?, dcontents=?, dwriter=? WHERE dnum=?;",
//	 "DELETE FROM stu WHERE dnum=?;"
	
	String[] SQLS = {
			 "INSERT INTO stu (sname, stel, sgrade, sclass)" + "VALUES (?,?,?,?);",
			 "SELECT * FROM stu ORDER BY sid DESC;",
			 "SELECT * FROM stu WHERE sname = ?; ",
			 "SELECT * FROM stu WHERE stel = ?; ",
			 "SELECT * FROM stu WHERE sgrade = ?; ",
			 "SELECT * FROM stu WHERE sclass = ?; ",
			 "SELECT * FROM stu WHERE sid = ?;",
			 "UPDATE stu SET sname=?, stel=?, sgrade=?, sclass=? WHERE sid=?;",
			 "DELETE FROM stu WHERE sid = ?;"
	};
	
}