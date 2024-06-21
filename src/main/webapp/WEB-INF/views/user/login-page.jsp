<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>로그인</title>
        <link rel="stylesheet" href="/assets/css/login-page.css" />
        <link rel="stylesheet" href="/assets/css/common.css" />
    </head>
    <body>
        <div class="login-wrap">
            <a class="home-btn" href="/"><i class="fas fa-long-arrow-alt-left"></i></a>
            <div class="login-html" data-sign-type="${enterType}">
                <input
                    id="tab-1"
                    type="radio"
                    name="tab"
                    class="sign-in"
                    checked
                /><label for="tab-1" class="tab">로그인</label>
                <input
                    id="tab-2"
                    type="radio"
                    name="tab"
                    class="sign-up"
                /><label for="tab-2" class="tab">회원가입</label>
                <div class="login-form">
                    <div class="sign-in-htm">
                        <form action="/user/sign-in" method="post">
                            <div class="group">
                                <label for="login-email" class="label"
                                    >이메일</label
                                >
                                <input
                                    id="login-email"
                                    type="text"
                                    name="email"
                                    class="input"
                                    placeholder="이메일"
                                    required
                                />
                            </div>
                            <div class="group">
                                <label for="login-password" class="label"
                                    >비밀번호</label
                                >
                                <input
                                    id="login-password"
                                    type="password"
                                    name="password"
                                    class="input"
                                    placeholder="비밀번호"
                                    data-type="password"
                                    required
                                />
                            </div>
                            <div class="group">
                                <input
                                    id="login-autoLogin"
                                    type="checkbox"
                                    class="check"
                                    name="autoLogin"
                                />
                                <label for="login-autoLogin"
                                    ><span class="icon"></span> 자동
                                    로그인</label
                                >
                            </div>
                            <div class="group">
                                <input
                                    type="submit"
                                    class="button"
                                    value="로그인"
                                />
                            </div>
                            <div class="hr"></div>
                        </form>
                    </div>
                    <div class="sign-up-htm">
                        <form action="/user/sign-up" method="post" id="signUpForm">
                            <div class="group">
                                <label for="email" class="label">이메일</label>
                                <input
                                    id="email"
                                    type="text"
                                    name="email"
                                    class="input"
                                    placeholder="이메일 주소"
                                    required
                                />
                                <span id="emailChk"
                                    ><i class="fas fa-pen"></i
                                ></span>
                            </div>
                            <div class="group">
                                <label for="nickname" class="label"
                                    >닉네임</label
                                >
                                <input
                                    id="nickname"
                                    type="text"
                                    name="nickname"
                                    class="input"
                                    placeholder="닉네임"
                                    required
                                />
                                <span id="nicknameChk"
                                    ><i class="fas fa-pen"></i
                                ></span>
                            </div>
                            <div class="group">
                                <label for="password" class="label"
                                    >비밀번호</label
                                >
                                <input
                                    id="password"
                                    type="password"
                                    name="password"
                                    class="input"
                                    placeholder="비밀번호"
                                    data-type="password"
                                    required
                                />
                                <span id="pwChk"
                                    ><i class="fas fa-pen"></i
                                ></span>
                            </div>
                            <div class="group">
                                <label for="password_check" class="label"
                                    >비밀번호 확인</label
                                >
                                <input
                                    id="password_check"
                                    type="password"
                                    class="input"
                                    placeholder="비밀번호 확인"
                                    data-type="password"
                                    required
                                />
                                <span id="pwChk2"
                                    ><i class="fas fa-pen"></i
                                ></span>
                            </div>
                            <div class="group">
                                <input
                                    type="submit"
                                    class="button"
                                    id="signup-btn"
                                    value="회원가입"
                                />
                            </div>
                            <div class="hr"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="module" src="/assets/js/seongjin/validation.js"></script>
        <script>
            if(document.querySelector(".login-html").dataset.signType === "signUp") {
                document.querySelector("#tab-2").checked = true;
            }
            const result = "${result}";
            console.log("result:" + result);

            if (result === "NO_EMAIL") {
                alert("존재하지 않는 이메일입니다.");
            } else if (result === "NO_PW") {
                alert("비밀번호가 틀렸습니다.");
            }

        </script>
    </body>
</html>
