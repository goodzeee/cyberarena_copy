<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/kibeom/register.css">
    <title>토론 등록</title>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<main>
    <div class="content-wrap">
        <div class="modal-content">
            <div class="modal-left">
                <h1>토론 등록</h1>
                <form action="/discussion/register" method="POST" id="discussionForm">
                    <input type="hidden" name="email" value="${login.email}">
                    <label>
                        # 닉네임 : <input type="text" id="nickname" value="${login.nickname}" readonly>
                    </label>
                    <label>
                        # 미디어 :
                        <input class="mediaName" type="text" name="mediaName" id="searchMediaInput" placeholder="검색어를 입력해주세요.">
                    </label>
                    <label>
                        # 주제 :<br> <input type="text" id="title" name="discussionTitle">
                    </label>
                    <br>
                    <label>
                        # 세부 내용 : <br><textarea type="text" id="detail" name="discussionOffer"></textarea>
                    </label>
                    <br>
                    <label>
                        <div class="bottom-wrap">
                            <input type="submit" id="finish" value="작성 완료">
                            <button type="button" id="cancelButton" onclick="window.location.href='/discussion/list'">작성
                                취소
                            </button>
                        </div>
                    </label>
                </form>
            </div>
<%--            <div class="modal-right">--%>
<%--                <!-- 추가적인 콘텐츠가 들어갈 수 있는 영역 -->--%>
<%--            </div>--%>
        </div>
    </div>
    <div class="fetch-wrap">
        <!-- 검색 결과를 보여주는 영역 -->
    </div>
</main>
<%@ include file="../include/footer.jsp" %>
<script type="module" src="/assets/js/kibeom/getMedia.js"></script>
<script type="text/javascript" src="/assets/js/kibeom/register.js"></script>
</body>
</html>
