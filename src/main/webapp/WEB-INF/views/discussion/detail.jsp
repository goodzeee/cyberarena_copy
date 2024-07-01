<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/kibeom/detail.css">
</head>
<body>

<div id="backdrop" class="hide"></div>

<%@ include file="../include/header.jsp" %>
<div class="content-wrap">
    <button
            class="list-btn"
            type="button"
            onclick="window.location.href='${ref}'"
    >
        목록
    </button>
    <div class="discussion-card">

        <div class="discussion-header">
            <h1 class="discussion-title">${found.discussionTitle}</h1>
            <div class="discussion-nickname">작성자: ${found.nickname}</div>
            <div class="discussion-nickname">미디어: ${found.mediaTitle}</div>
            <div class="discussion-info"></div>
        </div>
        <div class="discussion-body">
            <span class="discussion-date">작성 시간 : ${found.formattedDiscussionCreatedAt}</span>
            <span class="discussion-date">댓글 : [${count}]</span>
        </div>
        <c:if test="${login.nickname == found.nickname}">
            <button id="modify">수정</button>

            <button class="del-btn" data-href="/discussion/remove?dno=${found.discussionNo}">삭제</button>

        </c:if>
        <div class="discussion-actions">
            <h4>내용</h4>
        </div>
        <br>
        <div class="discussion-offer">${found.discussionOffer}</div>
    </div>
    <div class="comment-section">
        <h2>댓글</h2>
        <div id="comments">
            <!-- 댓글 목록 -->
        </div>
        <c:if test="${login != null}">
            <div class="comment-form">
                <form id="commentForm">
                    <textarea id="reply-textarea" placeholder="댓글을 입력하세요..." name="discussionReplyContent"></textarea>
                    <input type="hidden" value="${found.discussionNo}" name="discussionNo">
                    <input type="hidden" value="${login.email}" name="email">
                    <button type="button" id="submitComment" class="submit-comment">댓글 등록</button>
                </form>
            </div>
        </c:if>
        <c:if test="${login == null}">
            <div class="comment-form">
                <div class="message" >댓글 작성은 로그인 후 가능합니다.</div>
                <div class="button-wrap">
                    <button onclick="window.location.href = `/user/sign-in`">로그인</button>
                </div>
            </div>
        </c:if>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>


<%-- 수정 모달 --%>
<div class="modal-wrap" id="modal">
    <div class="modal-content">
        <div class="modal-left">
            <h1>토론 수정</h1>
            <form action="/discussion/modify" method="POST">
                <%--        <input type="hidden" name="email" value="${login.email}">--%>
                <input type="hidden" name="discussionNo" value="${found.discussionNo}">
                <label>
                    # 닉네임 : <input type="text" value="${login.nickname}" readonly>
                </label>
                <label>
                    # 미디어 : <input type="text" name="mediaName" value="${found.mediaTitle}" readonly>
                </label>
                <label>
                    # 주제 : <input type="text" id="title" name="discussionTitle" value="${found.discussionTitle}">
                </label>
                <label>
                    # 세부 내용 : <input type="text" id="detail" name="discussionOffer" value="${found.discussionOffer}">
                </label>

                <button type="submit" id="finish">수정 완료</button>
                <button type="button" id="cancelButton">수정 취소</button>

            </form>
        </div>
        <div class="modal-right">
            <form class="modal-search">


            </form>
            <div class="fetch-wrap">

            </div>
        </div>
    </div>
    <div class="modal-close" id="closeModalButton">닫기</div>
</div>
<%-- 수정 모달 끝 --%>


<script type="module" src="/assets/js/kibeom/getDiscussReply.js"></script>
<script type="module" src="/assets/js/kibeom/detail.js"></script>
<script>
    const $delBtn = document.querySelector('.del-btn');
    $delBtn.addEventListener('click', e => {
        window.location.href = "/discussion/remove?dno=${found.discussionNo}"
    })

    const $finishBtn = document.getElementById('finish');
    $finishBtn.addEventListener('click', e => {
        window.location.href = "/discussion/modify"
    })


</script>
</body>
</html>