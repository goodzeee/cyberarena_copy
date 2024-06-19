<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>회원가입</title>
        <link rel="stylesheet" href="/assets/css/sign-up.css" />
        <link rel="stylesheet" href="/assets/css/common.css" />
        <%@ include file="../include/static-head.jsp"%>

    </head>
    <body>
        <div class="container">
            <h1>회원가입</h1>
            <form
                action="/user/sign-up"
                name="signUp"
                id="signUpForm"
                method="post"
                enctype="multipart/form-data"
            >
                <p id="email"><input type="email" name="email" placeholder="이메일" /><span id="emailChk"><i class="fas fa-pen"></i></span></p>
                <p id="nickname"><input type="text" name="nickname" placeholder="닉네임" /><span id="nicknameChk"><i class="fas fa-pen"></i></span></p>
                <p id="password"><input type="password" name="password" placeholder="비밀번호" /><span id="pwChk"><i class="fas fa-pen"></i></span></p>
                <p id="password_check"><input type="password" placeholder="비밀번호확인" /><span id="pwChk2"><i class="fas fa-pen"></i></span></p>
                <button type="submit" id="signup-btn">회원가입하기</button>
            </form>
        </div>
        <script type="module" src="/assets/js/seongjin/validation.js"></script>
    </body>
</html>
