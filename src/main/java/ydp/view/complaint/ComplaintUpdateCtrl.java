package ydp.view.complaint;

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
import ydp.biz.complaint.ComplaintVO;

@WebServlet("/complaintupdate.do")
public class ComplaintUpdateCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
	
		
		int comid = Integer.parseInt(request.getParameter("comid")) ;
		ComplaintDAO cdao = new ComplaintDAO();
		ComplaintVO complaint  = cdao.getOneComplaint(comid);
		
		if(memId ==null || memId.length() ==0) { // 비로그인 유저 수정 클릭 시
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인해주세요.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
		}
		else if(!memId.equals(complaint.getMemId())) {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('작성자만 수정 가능합니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
		}

		else if(memId.equals(complaint.getMemId())) {
			request.setAttribute("complaint", complaint);
			RequestDispatcher dis = request.getRequestDispatcher("/sub/civil_complaint_update.jsp");
			dis.forward(request, response);
		}
		
	}
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("complaintupdate.do doPost");
		HttpSession session = request.getSession();
		//한글처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//폼데이터
		String comTitle = request.getParameter("comTitle");
		String comContent = request.getParameter("comcontent");
		int compublic = Integer.parseInt(request.getParameter("compublic"));
		int comid = Integer.parseInt(request.getParameter("comid"));
		ComplaintVO complaint = new ComplaintVO();
		complaint.setComTitle(comTitle);
		complaint.setComContent(comContent);
		complaint.setComPublic(compublic);
		complaint.setComId(comid);
		
		ComplaintDAO cdao = new ComplaintDAO();
		int result = cdao.updateComplaint(complaint);
		
		session.setAttribute("msg", "수정되었습니다.");
		
		response.sendRedirect("complaintlist.do");
	}
	

}
