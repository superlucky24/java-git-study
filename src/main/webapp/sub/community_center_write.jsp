<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자치회관 게시판 | 자치회관 | 영등포본동 주민센터</title>
    <link rel="shortcut icon" href="../images/favicon.ico">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/ydpb.css">
    <link rel="stylesheet" href="../css/ydpb_community_center_write.css">
    <script src="../js/jquery-1.12.4.min.js"></script>
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/prefixfree.min.js"></script>
    <script src="../js/ydpb.js"></script>
    <script>const menuName = '자치회관 게시판';</script>
    <script src="../js/ydpb_community_center_write.js"></script>
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
                                <a href="#">자치회관</a>
                            </span>
                            <span class="loc_arrow">></span>
                            <span class="loc_text">자치회관 게시판</span>
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
                            <form action="#" method="post" onreset="clearForm(this)">
                                <table class="table_form">
                                    <caption class="blind">자치회관 게시판 글쓰기</caption>
                                    <colgroup>
                                        <col class="col1">
                                        <col class="col2">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row">
                                                <label for="subject">제목</label>
                                                <span class="required_mark">필수항목</span>
                                            </th>
                                            <td>
                                                <input type="text" name="subject" id="subject" class="input form_input">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">
                                                <label for="content">내용</label>
                                                <span class="required_mark">필수항목</span>
                                            </th>
                                            <td>
                                                <textarea name="content" id="content" class="input" rows="10"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">
                                                <label for="tel">연락처</label>
                                                <span class="required_mark">필수항목</span>
                                            </th>
                                            <td>
                                                <input type="text" name="tel" id="tel" class="input form_input input_tel">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">파일</label></th>
                                            <td>
                                                <div class="file_group">
                                                    <div class="file_input">
                                                        <input type="file" name="file_1" id="file_1" multiple>
                                                        <span class="input disabled"></span>
                                                        <button type="button" class="clear_file" title="1번 파일 삭제">1번 파일 삭제</button>
                                                    </div>
                                                    <div class="file_btn">
                                                        <label for="file_1" class="btn btn_primary btn_sm">파일선택</label>
                                                    </div>
                                                    <div class="file_option">
                                                        <div class="checkbox">
                                                            <input type="checkbox" name="file_opt_1" id="file_opt_1">
                                                            <label for="file_opt_1">본문삽입</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <input type="text" name="file_text_1" id="file_text_1" class="input form_input" placeholder="첨부 이미지 대체 텍스트 입력">
                                                <div class="file_group">
                                                    <div class="file_input">
                                                        <input type="file" name="file_2" id="file_2" multiple>
                                                        <span class="input disabled"></span>
                                                        <button type="button" class="clear_file" title="2번 파일 삭제">2번 파일 삭제</button>
                                                    </div>
                                                    <div class="file_btn">
                                                        <label for="file_2" class="btn btn_primary btn_sm">파일선택</label>
                                                    </div>
                                                    <div class="file_option">
                                                        <div class="checkbox">
                                                            <input type="checkbox" name="file_opt_2" id="file_opt_2">
                                                            <label for="file_opt_2">본문삽입</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <input type="text" name="file_text_2" id="file_text_2" class="input form_input" placeholder="첨부 이미지 대체 텍스트 입력">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">공개여부</span></th>
                                            <td>
                                                <div class="radio">
                                                    <input type="radio" name="public" id="public_Y" checked>
                                                    <label for="public_Y">공개</label>
                                                </div>
                                                <div class="radio">
                                                    <input type="radio" name="public" id="public_N">
                                                    <label for="public_N">비공개</label>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="btns">
                                    <a href="community_center_list.jsp" class="btn btn_default">목록</a>
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