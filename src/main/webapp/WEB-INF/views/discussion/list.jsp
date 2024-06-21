<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="../include/static-head.jsp"%>

  <link rel="stylesheet" href="/assets/css/kibeom/discussion-list.css">

</head>

<body>
<div id="backdrop" class="none"></div>
<%@ include file="../include/header.jsp"%>

<div class="list-wrap">
  <h1>토론 리스트</h1>
  <button class="make-discussion">토론 생성하기</button>
  <c:forEach var="d" items="${dList}">
    <div class="card" data-dno="${d.discussionNo}">
      <p class="discussion-title">${d.discussionTitle}</p>
      <p class="nickname">${d.nickname}</p>
      <p class="discussion-offer">${d.discussionOffer}</p>
      <p class="discussion-created-at">${d.formattedDiscussionCreatedAt}</p>
    </div>
  </c:forEach>

</div>

<%@ include file="../include/footer.jsp"%>

<%-- Modal --%>
<div class="modal-wrap none" id="modal">
  <div class="modal-content">
    <div class="modal-left">
      <h1>토론 등록</h1>
      <form action="/discussion/register" method="POST">
        <input type="hidden" name="email" value="${login.email}">
        <label>
          # 닉네임 : <input type="text" id="nickname" value="${login.nickName}" readonly>
        </label>
        <label>
          # 미디어 : <input type="text" id="media" name="mediaName" readonly placeholder="하단의 검색창을 이용해주세요.">
        </label>
        <label>
          # 주제 : <input type="text" id="title" name="discussionTitle">
        </label>
        <label>
          # 세부 내용 : <input type="text" id="detail" name="discussionOffer">
        </label>
        <label>
          <input type="button" id="finish" value="작성 완료">
          <button type="button" id="cancelButton">작성 취소</button>
        </label>
      </form>
    </div>
    <div class="modal-right">
      <form class="modal-search">

        <label>
          <p>미디어 검색</p>
          <input class="modal-input" type="text" name="searchMedia" id="searchMediaInput" placeholder="검색어를 입력해주세요.">
        </label>
      </form>
      <div class="fetch-wrap">

      </div>
    </div>
  </div>
  <div class="modal-close" id="closeModalButton">닫기</div>
</div>



<script type="module" src="/assets/js/kibeom/getMedia.js"></script>
<script type="module" src="/assets/js/kibeom/list.js"></script>

</body>
</html>