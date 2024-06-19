<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>로그인</title>
        <link rel="stylesheet" href="/assets/css/sign-in.css" />
        <link rel="stylesheet" href="/assets/css/common.css" />
        <%@ include file="../include/static-head.jsp"%>
    </head>
    <body>
        <div class="login-container">
            <h1>로그인</h1>
            <form action="/user/sign-in" method="post">
                <input type="text" name="email" placeholder="이메일" required />
                <input
                    type="password"
                    name="password"
                    placeholder="비밀번호"
                    required
                />
                <div class="auto-login">
                    <label>
                        <input type="checkbox" name="autoLogin" /> 자동 로그인
                    </label>
                </div>
                <button type="submit">로그인</button>
                <a href="/user/sign-up" id="sign-up">회원가입</a>
            </form>
        </div>
    </body>
</html>
