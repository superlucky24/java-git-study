package ydp.view.complaint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ComplaintListCtrl
 */
@WebServlet("/complaintlist.do")
public class ComplaintListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // #1. 페이징 처리 = (((추후에 구현)))
		  // 화면에 보여질 게시글의 갯수를 지정 = 한페이지당 게시글 10개
		  int pageSize = 10;
		  // 현재 보여지고 있는 페이지의 넘버값을 읽어들임
		  String pageNum = request.getParameter("pageNum");
		  // null 처리 = 처음 로딩시 값이 없으므로
		  if(pageNum == null) {
		     pageNum = "1";
		  }
		  // 전체 게시글의 갯수 = 이전, 이후 버튼이 필요할지 안 할지에 사용
		  int count = 0;
		  // JSP 페이지내에서 보여질 페이징 숫자값을 저장하는 변수
		  int number = 0;
		  // 현재 보여지고 있는 페이지 문자 -> 숫자형 캐스팅
		  int currentPage = Integer.parseInt(pageNum);
		  
		  // #2. 전체 게시글 갯수 가져오는 메서드
		  ComplaintDAO cdao = new ComplaintDAO();
		  count = cdao.getAllCount();

		  System.out.println("count" + count);
		  System.out.println("전체 게시글 갯수 SQL 실행");
		  // #3. 전체 페이지 시작번호 설정
		  System.out.println("현재 페이지 : " + currentPage);
		  System.out.println("페이지 크기 : " + pageSize);
		  // 시작
		  int startRow = (currentPage -1) * pageSize + 1;
		  // 끝
		  int endRow = currentPage * pageSize;
		  
		  // #4. 최신글 10개 기준으로 게시글 가져오기 
		  List<ComplaintVO> clist = cdao.getAllComplaint(startRow,endRow);
		  System.out.println("모든 게시글(10개씩) 가져오기 SQL 실행");
		  // 페이징 숫자 = 화면에 보일 페이지 숫자
		  number = count - (currentPage-1) * pageSize;
		  // 바인딩 -> 모든 레코드셋 가져오기 => 목록에서 출력
		  request.setAttribute("clist", clist);
		  
		  
		  // 페이징 데이터 바인딩
		  request.setAttribute("number", number); // 페이징 갯수
		  request.setAttribute("pageSize", pageSize); // 화면에 보여질 게시글 갯수
		  request.setAttribute("count", count); // 전체 게시글 갯수 => 이 데이터를 알아야 페이징 계산 가능
		  request.setAttribute("currentPage", currentPage); // 현재 페이지
		  
		  System.out.println("clist" + clist);
		  System.out.println("number" + number);
		  System.out.println("pageSize" + pageSize);
		  System.out.println("count" + count);
		  System.out.println("currentPage" + currentPage);

		  HttpSession session = request.getSession();
		  request.setAttribute("msg", (String)session.getAttribute("msg"));
		  System.out.println("넘어온 메시지: "+(String)session.getAttribute("msg"));
		  session.removeAttribute("msg");
		  //포워딩
		  RequestDispatcher dis = request.getRequestDispatcher("/sub/civil_complaint_list.jsp");
		  dis.forward(request, response);
	}
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
