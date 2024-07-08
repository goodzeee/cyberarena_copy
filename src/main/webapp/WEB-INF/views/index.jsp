<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>

    <%@ include file="include/static-head.jsp"%>
</head>


<body>
<div id="backdrop" class="none"></div>
<header class="main-header">
  <a href="/index">
      <div class="logo" >LOGO</div>
  </a>
  <c:if test="${login != null}">
      <div class="login-user"><a href="/user/mypage"> ${login.nickname} </a>님 환영합니다.</div>
  </c:if>

  <a href="#" class="menu-open">
      <span class="lnr lnr-menu"></span>
  </a>

<nav class="gnb">
      <a href="#" class="close">
          <span class="lnr lnr-cross"></span>
      </a>
      <ul>
          <c:choose>
              <c:when test="${login == null}">
                  <li><a href="/user/sign-up">회원 가입</a></li>
                  <li><a href="/user/sign-in">로그인</a></li>
              </c:when>
              <c:otherwise>
                  <li><a href="/user/mypage">마이페이지</a></li>
                  <li><a href="/user/sign-out">로그아웃</a></li>
              </c:otherwise>
          </c:choose>
                  <li><a href="/media/movie">영화</a></li>
                  <li><a href="/media/series">시리즈</a></li>
                  <li><a href="/media/book">도서</a></li>
                  <li><a href="/discussion/list">토론 게시판</a></li>
        </ul>
  </nav>
</header>

<main>
    <section class="main">
        <div class="main-box">
          <img class="main-logo" src="../assets/img/logo.png" />
          <h2>각종 미디어에 대한 <br>
            방구석 평론가들의 적극적이고 뜨거운 <br>
            토론을 기다립니다.</h2>
        </div>
        <div class="slide">
          <input type="radio" name="img_select" id="select_box1" value="select_box1" class="img_select" checked />
          <input type="radio" name="img_select" id="select_box2" value="select_box2"
          class="img_select" />
          <input type="radio" name="img_select" id="select_box3" value="select_box3"
          class="img_select" />
          <div class="slide-nav">
            <label class="label1" for="select_box1"></label>
            <label class="label2" for="select_box2"></label>
            <label class="label3" for="select_box3"></label>
        </div>
        <div>
          <ul id="imgbox">
            <li class="select_img select1">
              <img
                class="background only-desktop"
                src="../assets/img/movie.jpeg"
              />
            </li>
            <li class="select_img select2">
              <img
                class="background only-desktop"
                src="../assets/img/series.jpg"
              />
            </li>
            <li class="select_img select3">
              <img
                class="background only-desktop"
                src="../assets/img/book.jpg"
              />
            </li>
          </ul>
        </div>
      </div>
    </section>
</main>


<!-- footer -->
<footer class="main-footer">
  <div class="footer-wrap">
      <div class="image-wrap">
          <img class="main-logo" src="../assets/img/logo2.png"/>
      </div>
      <div class="footer-text">
          © cyber-arena-club 2024
          All Rights Reseved
      </div>
  </div>
</footer>
<!-- footer end -->


</body>
<script>
    document.addEventListener("click", e => {
      if(!e.target.matches(".background")) return;
      const imgList = document.querySelectorAll(".img_select");
      if (imgList[0].checked) {
        window.location.href = 'media/movie';
      } else if (imgList[1].checked) {
        window.location.href = 'media/series';
      } else {
        window.location.href = 'media/book';
      }
      console.log("document",e.target);
    })


    let currentIndex = 0;
    const radioButtons = document.querySelectorAll('.img_select');
    const totalImages = radioButtons.length;

    setInterval(() => {
      currentIndex = (currentIndex + 1) % totalImages;
      radioButtons[currentIndex].checked = true;
    }, 5000);
</script>
</html>
