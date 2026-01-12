package ydp.biz.complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ComplaintDAO {
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
	
	// 전체 민원 갯수 확인
	public int getAllCount() {
		int count = 0;
		dbConn();
		try {
			String sql = "SELECT count(*) FROM tbl_complaint";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println("getAllCount rs.next()" + count);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//게시글 페이지크기만큼 조회
	public List<ComplaintVO> getAllComplaint(int start, int end){
	
		List<ComplaintVO> clist = new ArrayList<ComplaintVO>();
		dbConn();
		try {
			System.out.println("sql getAllComplaint");
			String sql = "select * from (select A.* ,Rownum Rnum from "
					+ "(select * from tbl_complaint order by comId desc)A) "
					+ "where Rnum >= ? and Rnum <= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			System.out.println(" getAllComplaint 실행");
			while(rs.next()) {
				ComplaintVO cvo = new ComplaintVO();
				cvo.setComId(rs.getInt(1));
				cvo.setComTitle(rs.getString(2));
				cvo.setComDate(rs.getDate(3));
				cvo.setComPublic(rs.getInt(4));
				cvo.setComStatus(rs.getString(5));
				cvo.setComContent(rs.getString(6));
				cvo.setComAnswer(rs.getString(7));
				cvo.setMemId(rs.getString(8));
				//System.out.println("가져온 제목 : "+rs.getString(2));
				clist.add(cvo);
				System.out.println(clist);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	
	//게시글 상세보기
	public ComplaintVO getOneComplaint(int comId) {
		ComplaintVO cvo = new ComplaintVO();
		dbConn();
		try {
			String sql = "SELECT * FROM tbl_complaint WHERE comId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cvo.setComId(rs.getInt(1));
				cvo.setComTitle(rs.getString(2));
				cvo.setComDate(rs.getDate(3));
				cvo.setComPublic(rs.getInt(4));
				cvo.setComStatus(rs.getString(5));
				cvo.setComContent(rs.getString(6));
				cvo.setComAnswer(rs.getString(7));
				cvo.setMemId(rs.getString(8));
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cvo;
	}
	//게시글 검색
	public List<ComplaintVO> searchComplaint(String searchKeyword, int start, int end){
		List<ComplaintVO> clist = new ArrayList<ComplaintVO>();
		dbConn();
		try {
			System.out.println("sql getAllComplaint");
			String sql = "select * from (select A.* ,Rownum Rnum from "
					+ " (select * from tbl_complaint order by comId desc)A) "
					+ " where (Rnum >= ? and Rnum <= ?) ";
			if(searchKeyword!= null && !searchKeyword.equals("")) {
				sql += " and comTitle LIKE '%"+searchKeyword.trim()+"%' ";
			}
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			System.out.println(" getAllComplaint 실행");
			while(rs.next()) {
				ComplaintVO cvo = new ComplaintVO();
				cvo.setComId(rs.getInt(1));
				cvo.setComTitle(rs.getString(2));
				cvo.setComDate(rs.getDate(3));
				cvo.setComPublic(rs.getInt(4));
				cvo.setComStatus(rs.getString(5));
				cvo.setComContent(rs.getString(6));
				cvo.setComAnswer(rs.getString(7));
				cvo.setMemId(rs.getString(8));
				System.out.println("가져온 제목 : "+rs.getString(2));
				clist.add(cvo);
				System.out.println(clist);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	
	//게시글 쓰기
	public int insertComplaint(ComplaintVO cvo) {
		int result = 0;
		dbConn();
		try {
			String sql = "INSERT INTO tbl_complaint ( comId,    comTitle,    comPublic,    comContent,   memId) "
					+ " VALUES (     (select NVL(max(comId),0)+1 from tbl_complaint),"
					+ "    ?,     ?,    ?,    ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cvo.getComTitle());
			pstmt.setInt(2, cvo.getComPublic());
			pstmt.setString(3, cvo.getComContent());
			pstmt.setString(4, cvo.getMemId());
			result= pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//게시글 수정
	public int updateComplaint(ComplaintVO cvo) {
		int result = 0;
		dbConn();
		try {
			String sql = "UPDATE tbl_complaint "
					+ "SET "
					+ "    comTitle = ?, "
					+ "    comPublic = ?, "
					+ "    comContent = ? "
					+ "WHERE comId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cvo.getComTitle());
			pstmt.setInt(2, cvo.getComPublic());
			pstmt.setString(3, cvo.getComContent());
			pstmt.setInt(4, cvo.getComId());
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//게시글 삭제
	public int deleteComplaint(int comId) {
		int result = 0;
		dbConn();
		try {
			String sql = "DELETE FROM tbl_complaint WHERE comId = ?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, comId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
