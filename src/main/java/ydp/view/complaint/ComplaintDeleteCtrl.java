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
 * Servlet implementation class ComplaintDeleteCtrl
 */
@WebServlet("/complaintdelete.do")
public class ComplaintDeleteCtrl extends HttpServlet {
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
		System.out.println("비교 결과 : " + memId.equals(complaint.getMemId()) );
		
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
			out.print("alert('본인이 작성한 글만 삭제 가능합니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			return;
		}
		if(memId.equals(complaint.getMemId())) {// 작성한 회원일 경우
			//삭제 실행
			int result = cdao.deleteComplaint(comid);

			session.setAttribute("msg", "삭제되었습니다.");
			response.sendRedirect("complaintlist.do");	
			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
