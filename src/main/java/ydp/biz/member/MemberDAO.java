package ydp.biz.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=  null;
	//DB 연결 메서드
	public void dbConn() {
		try {
			Context initctx = new InitialContext();
			System.out.println("1. Context 생성 성공!");
			Context envctx = (Context)initctx.lookup("java:comp/env");
			System.out.println("2. Context 환경 생성 성공!");
			DataSource ds = (DataSource)envctx.lookup("jdbc/pool");
			System.out.println("3. DataSource 찾기 성공!");
			conn = ds.getConnection();
			System.out.println("4. DB 연결 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//eom
	
	// 회원가입 
	public int insertMember(MemberVO mvo) {
		int result = 0;
		dbConn();
		try {
			String sql = "INSERT INTO tbl_member ("
					+ "    memId,"
					+ "    memName,"
					+ "    memBirth,"
					+ "    memGender,"
					+ "    memPassword,"
					+ "    memAddress,"
					+ "    memAddressDetail,"
					+ "    memTel,"
					+ "    memPhone,"
					+ "    memEmail,"
					+ "    memNews"
					+ ") VALUES ("
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?,"
					+ "    ?"
					+ ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getMemId());
			pstmt.setString(2, mvo.getMemName());
			pstmt.setDate(3, mvo.getMemBirth());
			pstmt.setString(4, mvo.getMemGender());
			pstmt.setString(5, mvo.getMemPassword());
			pstmt.setString(6, mvo.getMemAddress());
			pstmt.setString(7, mvo.getMemAddressDetail());
			pstmt.setString(8, mvo.getMemTel());
			pstmt.setString(9, mvo.getMemPhone());
			pstmt.setString(10, mvo.getMemEmail());
			pstmt.setString(11, mvo.getMemNews());
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//아이디 중복 확인
	   public boolean isIdExists(String memId) {   
	      boolean cnt = false;
	       try {
	          dbConn();
	          String sql = "SELECT COUNT(*) FROM tbl_member WHERE memid=?";
	          pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, memId);
	           ResultSet rs = pstmt.executeQuery();
	           if (rs.next()) {
	               cnt = (rs.getInt(1) > 0); // 이미 존재하면 true
	           }
	           rs.close();
	           pstmt.close();
	           conn.close();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return cnt;
	   }
	
	
	// 로그인 회원 확인
	public MemberVO loginMember(String id, String ps) {
		MemberVO mvo = new MemberVO();
		dbConn();
		try {
			String sql = "SELECT * FROM tbl_member WHERE memId = ? and mempassword=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, ps);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mvo.setMemId(rs.getString("memId"));
				mvo.setMemName(rs.getString("memName"));
				mvo.setMemBirth(rs.getDate("memBirth"));
				mvo.setMemGender(rs.getString("memGender"));
				mvo.setMemPassword(rs.getString("memPassword"));
				mvo.setMemAddress(rs.getString("memAddress"));
				mvo.setMemAddressDetail(rs.getString("memAddressDetail"));
				mvo.setMemTel(rs.getString("memTel"));
				mvo.setMemPhone(rs.getString("memPhone"));
				mvo.setMemEmail(rs.getString("memEmail"));
				mvo.setMemNews(rs.getString("memNews"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvo;
	}
	
	
	
}
