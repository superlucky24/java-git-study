<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>민원신청 | 우리동이야기 | 영등포본동 주민센터</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ydpb.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ydpb_community_center_view.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/prefixfree.min.js"></script>
    <script>const menuName = '민원신청';</script>
    <script src="${pageContext.request.contextPath}/js/ydpb.js"></script>
</head>
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
                                        <a href="#" class="loc_sns_icon1"> </a>
                                        <a href="#" class="loc_sns_icon2"> </a>
                                        <a href="#" class="loc_sns_icon3"> </a>
                                        <a href="#" class="loc_sns_icon4"> </a>
                                        <a href="#" class="loc_sns_icon5"> </a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- location -->

                    <!-- contents -->
                    <div class="contents" id="board">
                        <div class="board clearfix">
                            <div class="bbs_wrap">
                                <div class="bbs_info">
                                    <div class="bbs_count">
                                        <span>
                                            신청일
                                            <strong><fmt:formatDate value="${complaint.comDate }" pattern="yyyy.MM.dd"/></strong>
                                        </span>
                                    </div>
                                </div>
                                <table class="p-table">
                                    <colgroup>
                                        <col class="w20p">
                                        <col>
                                    </colgroup>
                                    <tbody class="p-tb">
                                        <tr>
                                            <td colspan="2"><span class="td_title">${complaint.comTitle }</span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class="td_text">
                                                ${complaint.comContent }
                                            </td>
                                        </tr>
                                        <c:if test="${not empty complaint.comStatus}">
                                        	
	                                        <tr>
	                                            <th scope="row">처리상황</th>
	                                            <td>${complaint.comStatus }</td>
	                                        </tr>
	                                        <tr>
	                                            <th scope="row">종합의견</th>
	                                            <td>
	                                                ${complaint.comAnswer }
	                                            </td>
	                                        </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                                <div class="form_btns_wrap">
                                	<c:if test="${not empty memId }">
	                                    <div class="btns">
	                                        <a href="${pageContext.request.contextPath}/complaintupdate.do?comid=${complaint.comId }" class="btn btn_bordered update">수정</a>
	                                        <a href="${pageContext.request.contextPath}/complaintdelete.do?comid=${complaint.comId }" class="btn btn_bordered delete" > 
	                                        	삭제  
	                                       	</a>
	                                    </div>
                                	</c:if>
                                    <div class="btns">
                                        <a href="complaintlist.do" class="p-btn">
                                            목록
                                            <span class="p-btn_write"></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
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
