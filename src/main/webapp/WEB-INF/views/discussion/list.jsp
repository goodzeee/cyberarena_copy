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
  <c:forEach var="d" items="${dList}">
    <div class="card" data-dno="${d.discussionNo}">
      <p class="discussion-title">${d.discussionTitle}</p>
      <p class="nickname">${d.nickname}</p>
      <p class="discussion-offer">${d.discussionOffer}</p>
      <p class="discussion-created-at">${d.formattedDiscussionCreatedAt}</p>
    </div>
  </c:forEach>
  <button class="make-discussion">토론 생성하기</button>
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
          # 닉네임 : <input type="text" value="${login.nickName}" readonly>
        </label>
        <label>
          # 미디어 : <input type="text" name="mediaName" readonly>
        </label>
        <label>
          # 주제 : <input type="text" name="discussionTitle">
        </label>
        <label>
          # 세부 내용 : <input type="text" name="discussionOffer">
        </label>
        <label>
          <button type="submit">작성 완료</button>
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
<script>

  // backdrop 해야함

  const $makeBtn = document.querySelector('.make-discussion')
  const $modal = document.getElementById('modal');
  const $backdrop = document.getElementById('backdrop')
  $makeBtn.addEventListener('click', e => {
    $modal.classList.remove('none')
    $backdrop.classList.remove('none')
  })

  const $detail = document.querySelector('.list-wrap')
  document.addEventListener('click', e => {
    if (!e.target.closest('.card')) return;
    const dno = e.target.closest('.card').dataset.dno;
    window.location.href = '/discussion/detail?dno=' + dno;
  })




</script>
</body>
</html>