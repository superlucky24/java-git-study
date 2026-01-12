<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>민원신청 | 우리동이야기 | 영등포본동 주민센터</title>
    <link rel="shortcut icon" href="./images/favicon.ico">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/ydpb.css">
    <link rel="stylesheet" href="./css/ydpb_community_center_list.css">
    <script src="./js/jquery-1.12.4.min.js"></script>
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script src="./js/prefixfree.min.js"></script>
    <script>const menuName = '민원신청';</script>
    <script src="./js/ydpb.js"></script>
</head>
<c:if test="${not empty msg }">
	<script type="text/javascript">
		console.log('${msg}');
		alert('${msg}');
	</script>
</c:if>
<body>
    <div id="wrap">
        <!-- header & search -->
        <jsp:include page="../includes/header.jsp" />
        <!-- //header & search -->

        <!-- container -->
        <div class="container">
            <div class="contents_wrap safe_area">
                <!-- side menu -->
        		<jsp:include page="../includes/sidemenu.jsp" />
                <!-- //side menu -->

                <!-- main -->
                <main>
                    <!-- location -->
                    <div class="location">
                        <div class="location_title">
                            <h2>영등포본동 주민센터</h2>
                        </div>
                        <div class="location_path">
                            <span class="loc_home">홈</span>
                            <span class="loc_arrow">></span>
                            <span class="loc_text">
                                <a href="../">동주민센터</a>
                            </span>
                            <span class="loc_arrow">></span>
                            <span class="loc_text">
                                <a href="../">영등포본동</a>
                            </span>
                            <span class="loc_arrow">></span>
                            <span class="loc_text">
                                <a href="../">우리동이야기</a>
                            </span>
                            <span class="loc_arrow">></span>
                            <span class="loc_text">민원신청</span>
                        </div>
                        <div class="location_icon">
                            <ul class="loc_fixed_icon">
                                <li><button type="button" class="loc_print">프린트</button></li>
                                <li class="icon_sns">
                                    <button type="button" class="loc_sns">SNS</button>
                                    <div class="sns_list">
                                        <a href="#" class="loc_sns_icon1">a</a>
                                        <a href="#" class="loc_sns_icon2">a</a>
                                        <a href="#" class="loc_sns_icon3">a</a>
                                        <a href="#" class="loc_sns_icon4">a</a>
                                        <a href="#" class="loc_sns_icon5">a</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- location -->

                    <!-- contents -->
                    <div id="board" class="clearfix red_box">
                        <div id="contents">
                            <form action="complaintsearch.do" class="board_searchform">
                                <fieldset>
                                    <legend>게시물검색</legend>
                                    <div class="p-search clearfix ">
                                        <div class="p-form-group red_box">
                                            <label for="searchType" class="skip">검색항목선택</label>
                                            <select name="searchType" id="searchType" title="검색항목선택" class="p-input">
                                                <option value="title" selected="selected">제목</option>
                                            </select>
                                            
                                            <label for="searchKeyword" class="skip"></label>
                                            <input name="searchKeyword" id="searchKeyword" class="p-input" type="text" value>
                                            <span class="p-form-group__btn">
                                                <input value="검색" type="submit" class="p-button">
                                            </span>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                        <div class="p_ctrl">
                            <div class="p_control">
                                <span class="p-link_b">
<!--                                     <strong title="현재 1페이지" class="p_link active"> -->
<!--                                         1 -->
<!--                                     </strong> -->
<!--                                     <a href="#" class="p_link">2</a> -->
                                    <!-- 글목록 컨트롤러에서 넘어온 데이터들 사용 -->
						            <c:if test="${count > 0 }"> <!-- 전체 글갯수가 있으면 -->
						               <!-- 총글갯수가 10개라 가정  -->
						               <!-- 10%10==0 ? 0 : 1 ->참이므로 0 |  10/10+0 = 1  -->
						               <c:set var="pageCount" value="${count / pageSize + (count%pageSize == 0 ? 0 : 1) }" />
						               <!-- startPage = 1 -->
						               <c:set var="startPage" value="${1 }" />
						               <c:if test="${currentPage%10 != 0 }"> <!-- 시작페이지가 0이 아니면 -->
						                  <!-- fmt = 결과를 정수형으로 받기위해 => 정수부분만 파싱 -->
						                  <!-- result = 1/10 = 0.1  integerOnly 속성으로 인해 소수 무시-->
						                  <fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
						                  <!-- startPage = 0.1*10+1.. -->
						                  <c:set var="startPage" value="${result*10+1 }"/>
						               </c:if>
						               <!-- 시작페이지가 0이면 | 10페이지%10-->
						               <c:if test="${currentPage%10 == 0 }">
						                  <c:set var="startPage" value="${(result-1)*10+1 }"/>
						               </c:if>
						               
						               <!-- endPage = 화면에 보여질 페이지 숫자 -->
						               <c:set var="pageBlock" value="${10 }" />
						               <c:set var="endPage" value="${startPage+pageBlock-1 }"/>
						               
						               <!-- 마지막 페이지가 10보다 크면 -->
						               <c:if test="${endPage > pageCount }">
						                  <c:set var="endPage" value="${pageCount }" />
							              <c:set var="realEndPage" value="${endPage-(endPage%1)}"/>
						               </c:if>
						              
						            </c:if>
                                </span>
                            </div>
                        </div>
                        <div class="page_cou clearfix">
                            <p>
                                총
                                <em class="em_black">${clist.size()}</em>
                                개 &nbsp;[
                                <em class="em_b_black">${currentPage}</em>
                                / <fmt:formatNumber type="number" maxFractionDigits="0" value="${realEndPage }" /> 페이지]
                            </p>
                        </div>
                        <table class="p_table">
                            <colgroup>
                                <col style="width: 100px;">
                                <col>
                                <col style="width: 120px;">
                                <col style="width: 120px;">
                                <col style="width: 100px;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">번호</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">신청일</th>
                                    <th scope="col">공개여부</th>
                                    <th scope="col">처리상황</th>
                                </tr>
                            </thead>
                            <tbody class="tb_text">
                               	<c:forEach items="${clist }" var="complaint" varStatus="status">
                                	<tr>
	                                    <td>${complaint.comId }</td>
	                                    <td class="p_sub"><a href="complaintview.do?comid=${complaint.comId }">${complaint.comTitle }</a></td>
	                                    <td> <fmt:formatDate value="${ complaint.comDate}" pattern="yyyy.MM.dd"/> </td>
	                                     <c:choose>
	                                     	<c:when test="${complaint.comPublic==0}">
	                                     		<td>N</td>
	                                     	</c:when>
	                                     	<c:when test="${complaint.comPublic==1}">
	                                     		<td>Y</td>
	                                     	</c:when>
	                                     </c:choose>
	                                    <td>${complaint.comStatus }</td>
                                	</tr>
                               	</c:forEach>
                            </tbody>
                        </table>
                        
                        <!-- 페이징 -->
                        <div class="p_ctrl">
                            <div class="p_control">
                                <span class="p-link_b">
<!--                                     <strong title="현재 1페이지" class="p_link active"> -->
<!--                                         1 -->
<!--                                     </strong> -->
<!--                                     <a href="#" class="p_link">2</a> -->
                                    <!-- 글목록 컨트롤러에서 넘어온 데이터들 사용 -->
						            <c:if test="${count > 0 }">						               
						               <!-- 이전 = 10개이상이면 이전 출력 -->
						               <c:if test="${startPage > 10 }">
						                  <a href="complaintlistctrl.do?pageNum=${startPage-10 }">[이전]</a>   
						               </c:if>
						               
						               <!-- 페이징 처리 = 카운터 클릭시 링크 -->
						               <c:forEach var="i" begin="${startPage }" end="${endPage }">
							               <c:if test="${currentPage==i }">							             
								               	<strong title="현재페이지" class="p_link active">
		                                       		 ${i }
		                                    	</strong>
							               </c:if>
							               <c:if test="${currentPage!=i }">							             
								               	<strong title="현재 1페이지" class="p_link active">
		                                       		 <a href="complaintlistctrl.do?pageNum=${i }" class="p_link active">${i}</a>
		                                    	</strong>
							               </c:if>
						                  
						               </c:forEach>
						               
						               <!-- 다음 -->
						               <c:if test="${endPage < pageCount }">
						                  <a href="complaintlistctrl.do?pageNum=${startPage+10 }">[다음]</a>   
						               </c:if>
						            </c:if>
                                </span>
                            </div>
                        </div>
                        <!-- /페이징 -->
                        
                        <div class="board_btm_btns">
                            <a href="complaintwrite.do" class="btn btn_write">글쓰기</a>
                        </div>
                    </div>
                    <!-- //contents -->
                    
                    <!-- charge info -->
                    <div class="charge_info">
                        <ul>
                            <li>
                                <span class="info_name">담당부서</span>
                                <span>영등포본동</span>
                            </li>
                            <li>
                                <span class="info_name">담당전화번호</span>
                                <span>02-2670-1026</span>
                            </li>
                        </ul>
                    </div>
                    <!-- //charge info -->
                </main>
                <!-- //main -->
            </div>
        </div>
        <!-- //container -->

        <!-- footer -->
        <jsp:include page="../includes/footer.jsp" />
        <!-- //footer -->
    </div>
</body>
</html>
