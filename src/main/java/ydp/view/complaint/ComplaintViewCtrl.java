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

/**
 * Servlet implementation class ComplaintViewCtrl
 */
@WebServlet("/complaintview.do")
public class ComplaintViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String memId= (String)session.getAttribute("memId");
		
		int comid = Integer.parseInt(request.getParameter("comid")) ;
		ComplaintDAO cdao = new ComplaintDAO();
		ComplaintVO complaint= cdao.getOneComplaint(comid);

		System.out.println("로그인 사용자 : " +memId);
		System.out.println("작성자 : " +complaint.getMemId());
		if(complaint.getComPublic()==1 ) {//공개 글의 경우 그냥 열람 가능
			System.out.println("공개글 열람");
			request.setAttribute("complaint", complaint);
			
			RequestDispatcher dis = request.getRequestDispatcher("/sub/civil_complaint_view.jsp");
			dis.forward(request, response);	
		}
		else { //비공개글의 경우 

			System.out.println("비공개글 열람");	
			if( memId==null) { // 비로그인 유저
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인해주세요.');");
				out.print("history.go(-1);");
				out.print("</script>");
				out.close();
				return;
			}
			
			if( !memId.equals(complaint.getMemId())) { // 작성한 유저가 아닌 경우
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('본인이 작성한 비공개글만 열람 가능합니다.');");
				out.print("history.go(-1);");
				out.print("</script>");
				out.close();
				return;
			}
			if(memId.equals(complaint.getMemId())) {// 작성한 회원일 경우
				request.setAttribute("complaint", complaint);
				request.setAttribute("msg", "수정되었습니다.");
				
				RequestDispatcher dis = request.getRequestDispatcher("/sub/civil_complaint_view.jsp");
				dis.forward(request, response);	
				return;
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
