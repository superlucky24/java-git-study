package ydp.view.complaint;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ydp.biz.complaint.ComplaintDAO;
import ydp.biz.complaint.ComplaintVO;

/**
 * Servlet implementation class ComplaintWriteCtrl
 */
@WebServlet("/complaintwrite.do")
public class ComplaintWriteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		if(memId==null || memId.isEmpty() || memId.length()==0  ) {
			//session.setAttribute("msg", "로그인 필요");
			response.sendRedirect("login.do");
			return;
		}
		else {
			RequestDispatcher dis =  request.getRequestDispatcher("/sub/civil_complaint_write.jsp");
			dis.forward(request, response);
		}
	}
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("complaintwrite doPost");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//vo
		ComplaintVO complaint = new ComplaintVO();
		//폼 데이터
		complaint.setComTitle(request.getParameter("comTitle"));
		complaint.setComPublic( Integer.parseInt(request.getParameter("compublic")) );
		complaint.setComContent(request.getParameter("comcontent"));
		// 회원아이디
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId") ;
		//테스트용
		//String memid="gh";		
		complaint.setMemId(memId);
		
		System.out.println("comTitle : "+complaint.getComTitle());
		System.out.println("compublic : "+complaint.getComPublic());
		System.out.println("comcontent : "+complaint.getComContent());
		
		ComplaintDAO cdao = new ComplaintDAO();
		int result = cdao.insertComplaint(complaint);
		response.sendRedirect("complaintlist.do");
	}

}
