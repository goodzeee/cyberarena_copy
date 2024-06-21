<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<header>
    <a href="#">
        <div class="logo">LOGO</div>
    </a>
    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="검색..." />
        <button>검색</button>
    </div>


    <a href="#" class="menu-open">
        <span class="menu-txt">MENU</span>
        <span class="lnr lnr-menu"></span>
    </a>
    </div>

    <nav class="gnb">
        <a href="#" class="close">
            <span class="lnr lnr-cross"></span>
        </a>
        <ul>
            <li><a href="#">회원 가입</a></li>
            <li><a href="/user/sign-in">로그인</a></li>
            <li><a href="/review/list/{mno}">영화</a></li>
            <li><a href="/review/list">시리즈</a></li>
            <li><a href="/review/list">도서</a></li>
            <li><a href="discussion/list">토론 게시판</a></li>
            <!--
            <li><a href="#">마이페이지</a></li>
            <li><a href="#">영화</a></li>
            <li><a href="#">시리즈</a></li>
            <li><a href="#">도서</a></li>
            <li><a href="#">토론 게시판</a></li>
            <li><a href="/user/sign-out">로그아웃</a></li>
            -->
        </ul>
    </nav>
</header>
