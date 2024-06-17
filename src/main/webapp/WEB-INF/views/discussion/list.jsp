<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="../include/static-head.jsp"%>
  <style>

    .modal-wrap {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 60%;
      flex-wrap: wrap;
      position: fixed;
      z-index: 9999;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      /* cursor: pointer; */
      background: lightgray;
      /* border: 1px solid #000; */

    }

    .modal-wrap .img-wrap {
      width: 47%;
    }
    .modal-wrap .img-wrap img {
      width: 80%;
      height: 80%;
      background: center/cover no-repeat fixed;
      overflow: hidden;

    }

    .modal-wrap .media-info {
      width: 47%;
    }

    .modal-wrap .modal-close {
      width: 6%;
      position: relative;
      bottom: 170px;
      right: 20px;
      border: 1px solid #000;
      justify-content: center;
      align-items: center;
      display: flex;
      background: gray;
      color: white;
      border-radius: 20px;
      font-size: 12px;
      cursor: pointer;

    }

    .media-info h2 {
      padding-bottom: 20px;
      border-bottom: 1px solid #000;
      padding-top: 20px;
    }

    .media-info .detail-info {
      padding-bottom: 20px;
      border-bottom: 1px solid #000;
      display: flex;
      justify-content: space-around;
      padding-top: 10px;
    }

    .media-info .description {
      padding-top: 30px;
    }

    .modal-wrap .reviews {
      width: 100%;
      border-top: 1px solid #000;
      margin-top: 30px;
    }

    .none {
      display: none;
    }

    #backdrop {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100vh;
      z-index: 10;
      background: rgba(0, 0, 0, 0.75);
    }
  </style>
</head>
<body>
<div id="backdrop" class="none"></div>
<%@ include file="../include/header.jsp"%>


<%--
    - 특정 미디어 선택
    - 주제 입력
    - 세부내용 입력
    - 토론 생성 버튼 클릭
    --%>
<div class="list-wrap">
  <h1>토론 리스트</h1>
  <c:forEach var="d" items= "${dList}">
    <div>${d} </div>
  </c:forEach>
  <button class="make-discussion">토론 생성하기</button>
</div>

<%@ include file="../include/footer.jsp"%>
<%--modal--%>
<div class="modal-wrap none">
  <h1>토론 등록</h1>
  <form action="/discussion/register" method="POST">
    <label>
      # 이메일 : <input type="text" name="email">
    </label>
    <label>
      # 미디어 : <input type="text" name="mediaName">
    </label>
    <label>
      # 주제 : <input type="text" name="discussionTitle">
    </label>
    <label>
      # 세부 내용 : <input type="text" name="discussionOffer">
    </label>
    <label>
      <button type="submit">작성 완료</button>
      <button id="cancle" type="button">작성 취소</button>
    </label>
  </form>
  <div class="modal-close">닫기</div>
</div>




<script>
  const $media = document.querySelector('.make-discussion')
  console.log($media);
  const $modal = document.querySelector('.modal-wrap')
  const $closeBtn = document.querySelector('.modal-close')
  // const $backdrop = document.getElementById('backdrop')
  console.log($closeBtn)

  $media.addEventListener('click', e => {
    console.log("버튼 클릭함!")
      $modal.classList.remove('none');
      // $backdrop.classList.remove('none')

  });

  $closeBtn.addEventListener('click', e => {
    $modal.classList.add('none')
    // $backdrop.classList.add('none')
  })


  // document.addEventListener('click', e => {

  //   if (!e.target.closest('.modal-wrap') && !e.target.closest('.media-card')) {
  //     $modal.classList.add('none');
  //     $backdrop.classList.add('none')
  //   }
  // });

</script>
</body>
</html>