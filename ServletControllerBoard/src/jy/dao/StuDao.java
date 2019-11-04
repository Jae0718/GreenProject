package jy.dao;

import jy.dto.*;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.tree.RowMapper;


public class StuDao implements IDaoConstants {
	private static StuDao instance = new StuDao();
	
	private StuDao(){
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static StuDao getInstance(){
		return instance;
	}
	
	public Connection connect(){
		Connection con = null;
		
		//컨넥션을 드라이버 매니저로부터 얻어온다
		try {
			con = DriverManager.getConnection
					(DBURL,UID, UPW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}	
	
	public void disconnect(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(Connection con, Student stu){
		int result = 0;
		PreparedStatement pStmt = null;
		String sql = SQLS[INSERT];
		try {
			pStmt= con.prepareStatement(sql);
			pStmt.setString(1, stu.getSname());
			pStmt.setString(2, stu.getStel());
			pStmt.setInt(3, stu.getSgrade());
			pStmt.setString(4, stu.getSclass());
			
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pStmt);
		}
		return result;
	}
	
	public void update (Connection con, Student stu){
		PreparedStatement pStmt = null;
		String sql = SQLS[UPDATE];
		try {
			pStmt= con.prepareStatement(sql);
			pStmt.setString(1, stu.getSname());
			pStmt.setString(2, stu.getStel());
			pStmt.setInt(3, stu.getSgrade());
			pStmt.setString(4, stu.getSclass());
			pStmt.setInt(5,stu.getSid());
			
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pStmt);
		}
	}
	
	
	public Student[] find(Connection con,String option ,String sValue){
		Student[]resultList = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		String sql = null;
		if(option.equals("sname")){
			 sql = SQLS[SELECT_BY_NAME];
		}else if(option.equals("stel")){
			sql = SQLS[SELECT_BY_TEL];
		}else if(option.equals("sgrade")){
			sql = SQLS[SELECT_BY_GRADE];
		}else if(option.equals("sid")){
			sql = SQLS[SELECT_BY_ID];
		}else{
			 sql = SQLS[SELECT_BY_CLASS];
		}
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, sValue);
			rs = pStmt.executeQuery();
			rs.last();
			int count = rs.getRow();
			resultList = new Student[count];
			rs.beforeFirst();
			
			int idx = 0;
			while(rs.next()){
				int sid = rs.getInt("sid");
				String sname = toEng(rs.getString("sname"));
				String stel = toEng(rs.getString("stel"));
				int sgrade = rs.getInt("sgrade");
				String sclass = toEng(rs.getString("sclass"));
				
				resultList[idx] = new Student(sid,sname,stel,sgrade,sclass);
				idx++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pStmt);
		}
		return resultList;
	}
	
	public void delete(Connection con, int sid){
		PreparedStatement pStmt = null;
		String sql = SQLS[DELETE];
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, sid);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pStmt);
		}
	}
	
	public Student[] getAll(Connection con){
		Student[] list = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String sql = SQLS[GETALL];
		
		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			rs.last();
			int count = rs.getRow();
			list = new Student[count];
			rs.beforeFirst();
			
			int idx = 0;
			while(rs.next()){
				int sid = rs.getInt("sid");
				String sname = toEng(rs.getString("sname"));
				String stel = toEng(rs.getString("stel"));
				int sgrade = rs.getInt("sgrade");
				String sclass = toEng(rs.getString("sclass"));
				
				list[idx] = new Student(sid,sname,stel,sgrade,sclass);
				idx++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs);
			close(pStmt);
		}
		return list;
	}
	private Student rowMapping (ResultSet rs)throws SQLException{
		int sid = rs.getInt("sid");
		String sname = toEng(rs.getString("sname"));
		String stel = toEng(rs.getString("stel"));
		int sgrade = rs.getInt("sgrade");
		String sclass = toEng(rs.getString("sclass"));
		
		return  new Student(sid,sname,stel,sgrade,sclass);
	}
	
	public Student[] getList(Connection con, int pageNum, int perPage){
		Student[] list = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String sql =  "SELECT * FROM stu ORDER BY sid DESC LIMIT ?, ?";
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, (pageNum-1)*perPage);
			pStmt.setInt(2, perPage);
			rs = pStmt.executeQuery();
			rs.last();
			list = new Student[rs.getRow()];
			rs.beforeFirst();
			
			int idx = 0;
			while(rs.next()){
				list[idx] = rowMapping(rs);
				idx++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rs);
			close(pStmt);
		}
		return list;
	}
	
	public int getTotalCount(Connection con,int perPage){
		int count = 0;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM stu;";
		
		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int totalcount = (count/perPage) + 1;
		
		if((count%perPage) == 0) {
			totalcount = (count/perPage);
		}
		
		return totalcount;
	}
	
	
	public void close(Statement stmt){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String toKor(String str){
		String en = null;
		
		try {
			en = new String(str.getBytes("euc_kr"),"8859_1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return en;
	}
	private String toEng(String str){
		String kor = null;
		
		try {
			kor = new String(str.getBytes("8859_1"),"euc_kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kor;
	}
	
	
}
