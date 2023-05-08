package com.myweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//DAO(Data Access Object)
//웹 프로그램에서 데이터베이스 CRUD작업이 요구될 때마다
//데이터베이스 접속 및 sql문 실행을 전담하는 비즈니스 로직으로 이루어진 객체
//무분별한 객체 생성을 막기 위해 싱글톤 디자인 패턴으로 구축합니다.
public class UserDAO {

	//커넥션 풀의 정보를 담을 변수를 선언.
	private DataSource ds;

	private UserDAO() {
		//커넥션 풀 정보 불러오기.
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/myOracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static UserDAO dao = new UserDAO();

	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}

	//////////////////////////////////////////////////////

	//회원 중복 여부 확인
	public boolean confirmId(String id) {
		String sql = "SELECT * FROM my_user WHERE user_id = ?";
		boolean flag = false;
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//회원 정보 저장
	public void insertUser(UserVO vo) {
		String sql = "INSERT INTO my_user VALUES(?,?,?,?,?)";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getUserPw());
			pstmt.setString(3, vo.getUserName());
			pstmt.setString(4, vo.getUserEmail());
			pstmt.setString(5, vo.getUserAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//로그인 판별
	public int userCheck(String id, String pw) {
		String sql = "SELECT user_pw FROM my_user WHERE user_id = ?";
		int result = 0;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbPw = rs.getString("user_pw");
				if(dbPw.equals(pw)) result =1;
				else result = 0;
			} else result = -1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//유저를 가져오는 메서드
	public UserVO getUserInfo(String id) {
		UserVO user = null;
		String sql = "SELECT * FROM my_user WHERE user_id= '" + id + "'";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if(rs.next()) {
				user = new UserVO(
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_name"),
						rs.getString("user_email"),
						rs.getString("user_address")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	//비밀번호 변경 메서드
	public void changePassword(String id, String newPw) {
		String sql = "UPDATE my_user SET user_pw = ? WHERE user_id = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//회원정보 수정 메서드
	public void updateUser(UserVO user) {
		String sql = "UPDATE my_user SET user_name = ? , user_email = ? , "
				+ "user_address = ?  WHERE user_id = ?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserAddress());
			pstmt.setString(4, user.getUserId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//회원 탈퇴 메서드
	public void deleteUser(String id) {
		String sql ="DELETE FROM my_user WHERE user_id = ? ";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
