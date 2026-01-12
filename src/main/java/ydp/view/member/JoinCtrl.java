package ydp.view.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ydp.biz.complaint.ComplaintVO;
import ydp.biz.member.MemberDAO;
import ydp.biz.member.MemberVO;

/**
 * Servlet implementation class JoinCtrl
 */
@WebServlet("/join.do")
public class JoinCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/member/join.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String memId= request.getParameter("memId");
		String memName = request.getParameter("memName");
		String mBirth =request.getParameter("memBirth");
		java.sql.Date memBirth  = null;
		if(mBirth!=null && mBirth!="" && !mBirth.isEmpty()) {
			memBirth = java.sql.Date.valueOf(mBirth);
		}
		String memGender = request.getParameter("memGender");
		String memPassword= request.getParameter("memPassword");
		String memPasswordRe= request.getParameter("memPasswordRe");
		String memAddress = request.getParameter("memAddress");
		String memAddressDetail = request.getParameter("memAddressDetail");
		String memTel = request.getParameter("memTel1");
		memTel += request.getParameter("memTel2");
		memTel += request.getParameter("memTel3");
		String memPhone = request.getParameter("memPhone1");
		memPhone += request.getParameter("memphone2");
		memPhone += request.getParameter("memphone3");
		String memEmail = request.getParameter("mememail1");
		memEmail+="@";
		memEmail += request.getParameter("mememail2");
		String memNews = request.getParameter("memNews");
		
		//데이터 체크
		System.out.println(memId);
		System.out.println(memName);
		System.out.println(memBirth);
		System.out.println(memGender);
		System.out.println(memPassword);
		System.out.println(memPasswordRe);
		System.out.println(memAddressDetail);
		System.out.println(memTel);
		System.out.println(memPhone);
		System.out.println(memEmail);
		System.out.println(memNews);
		
		//비밀번호 비교 일치 시
		if(memPassword.equals(memPasswordRe)) {
			MemberVO member = new MemberVO();
			member.setMemId(memId);
			member.setMemName(memName);
			member.setMemBirth(memBirth);
			member.setMemGender(memGender);
			member.setMemPassword(memPassword);
			member.setMemAddress(memAddress);
			member.setMemAddressDetail(memAddressDetail);
			member.setMemTel(memTel);
			member.setMemPhone(memPhone);
			member.setMemEmail(memEmail);
			member.setMemNews(memNews);
			
			MemberDAO mdao = new MemberDAO();
			// true : 중복 존재
			// false : 중복 없음=> 통과
			System.out.println("중복 아이디 확인 결과 : "+ mdao.isIdExists(memId));
			if(!mdao.isIdExists(memId)) {
				
				int result = mdao.insertMember(member);
				if(result==1) {
					String encodedMemName = URLEncoder.encode(memName, "UTF-8");
					response.sendRedirect("index.jsp?msg="+result+"&memName="+encodedMemName);			
				}
				//		else {
				//			PrintWriter out = response.getWriter();
				//			out.print("<script>");
				//			out.print("alert('회원가입에 실패하셨습니다.');");
				//			out.print("</script>");
				//			out.close();
				//		}
				
			}
			else {
				request.setAttribute("msg", "중복 아이디 사용불가");
				RequestDispatcher dis = request.getRequestDispatcher("/member/join.jsp");
				dis.forward(request, response);
			}
			
		}
		else { //비밀번호 불일치 시
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
		}
		
	}

}
