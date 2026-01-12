package ydp.view.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ydp.biz.complaint.ComplaintDAO;
import ydp.biz.member.MemberDAO;
import ydp.biz.member.MemberVO;

/**
 * Servlet implementation class LoginCtrl
 */
@WebServlet("/login.do")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("/member/login.jsp");
		dis.forward(request, response);
	}
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String memId= request.getParameter("memId");
		String memPassword= request.getParameter("memPassword");
		MemberDAO mdao = new MemberDAO();
		MemberVO member = mdao.loginMember(memId, memPassword);
		if(memId==null || memId.length()==0 ||memId=="") {
			request.setAttribute("msg", "로그인 실패");	
			RequestDispatcher dis = request.getRequestDispatcher("/member/login.jsp");
			dis.forward(request, response);		
		}
//		PrintWriter out = response.getWriter();
		if(member == null) {
			request.setAttribute("msg", "로그인 실패");
//			out.print("<script>");
//			out.print("alert('아이디 혹은 비밀번호가 일치하지 않습니다!');");
//			out.print("</script>");
//			out.close();
			RequestDispatcher dis = request.getRequestDispatcher("/member/login.jsp");
			dis.forward(request, response);
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("memId", memId);
//			out.print("<script>");
//			out.print("alert('환영합니다! "+memId+ "님!');");
//			out.print("</script>");
//			out.close();
			response.sendRedirect("index.jsp");
		}
	}

}
