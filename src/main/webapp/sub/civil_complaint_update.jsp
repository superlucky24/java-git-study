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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ydpb_community_center_write.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/prefixfree.min.js"></script>
    <script>const menuName = '민원신청';</script>
    <script src="${pageContext.request.contextPath}/js/ydpb.js"></script>
    <script src="${pageContext.request.contextPath}/js/ydpb_community_center_write.js"></script>
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
                    <div class="contents">
                        <div class="board_write">
                            <p class="text_alert alert_write"><span class="required_mark">필수항목</span> 표시는 필수 항목 입니다.</p>
                            <form action="complaintupdate.do" method="post" onreset="clearForm(this)">
                                <table class="table_form">
                                    <caption class="blind">민원신청 게시판 글수정</caption>
                                    <colgroup>
                                        <col class="col1">
                                        <col class="col2">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row">
                                                <label for="comTitle">제목</label>
                                                <span class="required_mark">필수항목</span>
                                            </th>
                                            <td>
                                                <input type="text" name="comTitle" id="comTitle" class="input form_input" maxlength="300" value="${complaint.comTitle }" required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">
                                                <label for="comcontent">내용</label>
                                                <span class="required_mark">필수항목</span>
                                            </th>
                                            <td>
                                                <textarea name="comcontent" id="comcontent" class="input" rows="10" maxlength="4000" required>${complaint.comContent }</textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">공개여부</th>
                                            <td>
                                            	<c:if test="${complaint.comPublic==1 }">
                                            	
		                                            <div class="radio">
		                                                <input type="radio" name="compublic" id="compublic1" value="1" checked>
		                                                <label for="compublic1">공개</label>
		                                            </div>
		                                            <div class="radio">
		                                                <input type="radio" name="compublic" id="compublic0" value="0">
		                                                <label for="compublic0">비공개</label>
		                                            </div>
                                            	</c:if>
                                            	<c:if test="${complaint.comPublic==0 }">
                                            	
		                                            <div class="radio">
		                                                <input type="radio" name="compublic" id="compublic1" value="1" >
		                                                <label for="compublic1">공개</label>
		                                            </div>
		                                            <div class="radio">
		                                                <input type="radio" name="compublic" id="compublic0" value="0" checked>
		                                                <label for="compublic0">비공개</label>
		                                            </div>
                                            	</c:if>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="btns">
                                    <a href="complaintlist.do" class="btn btn_default">목록</a>
                                    <!-- 글번호 히든 -->
                                    <input type="hidden" name="comid" value="${complaint.comId }" />
                                    <input type="submit" class="btn btn_write" value="저장">
                                    <input type="reset" class="btn btn_cancel" value="다시쓰기">
                                </div>
                            </form>
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