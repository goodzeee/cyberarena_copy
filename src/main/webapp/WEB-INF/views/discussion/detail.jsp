<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="../include/static-head.jsp"%>
  <link rel="stylesheet" href="/assets/css/kibeom/detail.css">
</head>
<body>

<%@ include file="../include/header.jsp"%>

<div class="content-wrap">
  <div class="discussion-card">
    <div class="discussion-header">
      <h1 class="discussion-title">${found.discussionTitle}</h1>
      <div class="discussion-nickname">작성자: ${found.nickname}</div>
      <div class="discussion-info"></div>
    </div>
    <div class="discussion-body">
      <span class="discussion-date">작성 시간 : ${found.formattedDiscussionCreatedAt}</span>
    </div>
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
    <div class="comment-form">
      <form id="commentForm">
        <textarea placeholder="댓글을 입력하세요..." name="discussionReplyContent"></textarea>
        <input type="hidden" value="${found.discussionNo}" name="discussionNo">
        <input type="hidden" value="${login.email}" name="email">
        <button type="button" id="submitComment" class="submit-comment">댓글 등록</button>
      </form>
    </div>
  </div>
</div>

<%@ include file="../include/footer.jsp"%>

<script type="module" src="/assets/js/kibeom/getDiscussReply.js"></script>
</body>
</html>