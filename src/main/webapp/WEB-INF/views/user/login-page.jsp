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
        <dialog id="find-dialog">
            <div class="dialog-wrap">
            </div>
        </dialog>
        <div class="login-wrap">
            <a class="home-btn" href="/"
                ><i class="fas fa-long-arrow-alt-left"></i
            ></a>
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
                            <button id="find-id">
                                이메일 찾기(식별코드 필요)
                            </button>
                            <button id="find-pw">
                                비밀번호 찾기(식별코드 필요)
                            </button>
                        </form>
                    </div>
                    <div class="sign-up-htm">
                        <form
                            action="/user/sign-up"
                            method="post"
                            id="signUpForm"
                        >
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
            const $dialog = document.querySelector("#find-dialog");
            const $modalWrap = $dialog.querySelector(".dialog-wrap");
            const $idBtn = document.querySelector("#find-id");
            const $pwBtn = document.querySelector("#find-pw");
            let newPwFlag = false;
            let newPwChkFlag = false;
            if (
                document.querySelector(".login-html").dataset.signType ===
                "signUp"
            ) {
                document.querySelector("#tab-2").checked = true;
            }
            const result = "${result}";
            console.log("result:" + result);

            if (result === "NO_EMAIL") {
                alert("존재하지 않는 이메일입니다.");
            } else if (result === "NO_PW") {
                alert("비밀번호가 틀렸습니다.");
            }

            function checkNewPass() {
                const $submit = document.querySelector(".find-pw-submit");
                const flag = !(newPwFlag && newPwChkFlag);

                if (flag) {
                    $submit.style.background = "gray";
                    $submit.disabled = true;
                } else {
                    $submit.style.background = "#4CAF50";
                    $submit.disabled = false;
                }
            }

            async function submitHandler(e) {
                const $codeInput = $dialog.querySelector(".code-input");
                const requestInfo = {
                    method: "POST",
                    headers: {
                        "content-type": "application/json",
                    },
                    body: JSON.stringify({
                        code: $codeInput.value,
                    }),
                };
                const res = await fetch("/user/find-id", requestInfo);
                if (res.status === 403) {
                    const msg = await res.text();
                    alert(msg);
                    return;
                }
                const email = await res.text();
                $modalWrap.innerHTML = `
                <div>내 가입 이메일: \${email}</div>
                <button class="after-email">비밀번호 찾기</button>
                `;
                document
                    .querySelector(".after-email")
                    .addEventListener("click", findPwHandler);
            }

            async function pwSubmitHandler(e) {
                const $codeInput = $dialog.querySelector(".code-input");
                const $findEmailInput =
                    $dialog.querySelector(".find-email-input");
                const requestInfo = {
                    method: "POST",
                    headers: {
                        "content-type": "application/json",
                    },
                    body: JSON.stringify({
                        code: $codeInput.value,
                        email: $findEmailInput.value,
                    }),
                };
                const res = await fetch("/user/find-pw", requestInfo);
                if (res.status === 403) {
                    const msg = await res.text();
                    alert(msg);
                    return;
                }
                const email = await res.text();
                $modalWrap.innerHTML = `
                <div>새 비밀번호</div><input class="new-pw" type="password">
                <span id="new-pw-val"></span>
                <div>비밀번호 확인</div><input class="new-pw-check" type="password">
                <span id="new-pw-chk-val"></span>
                <button class="find-pw-submit">비밀번호 변경하기</button>
                `;
                checkNewPass();
                const $newPwVal = $dialog.querySelector("#new-pw-val");
                const $newPwChkVal = $dialog.querySelector("#new-pw-chk-val");
                const $newPw = $dialog.querySelector(".new-pw");
                const $newPwChk = $dialog.querySelector(".new-pw-check");

                $newPw.addEventListener("keyup", (e) => {
                    const passwordPattern =
                        /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*?_~])[A-Za-z\d!@#$%^&*?_~]{8,}$/;

                    const pwValue = $newPw.value;
                    if (pwValue.length >= 8) {
                        if (pwValue.trim().length != pwValue.length) {
                            $newPw.style.borderColor = "red";
                            $newPwVal.innerHTML =
                                '<b class="warning">[비밀번호에 공백을 포함할 수 없습니다.]</b>';
                            newPwFlag = false;
                        } else if (!passwordPattern.test(pwValue)) {
                            $newPw.style.borderColor = "red";
                            $newPwVal.innerHTML =
                                '<b class="warning">>[비밀번호는 영문과 특수문자를 포함해야 합니다.]</b>';
                            newPwFlag = false;
                        } else {
                            $newPw.style.borderColor = "green";
                            $newPwVal.innerHTML =
                                '<b class="success">[사용할 수 있는 비밀번호입니다.]</b>';
                            newPwFlag = true;
                        }
                    } else {
                        $newPw.style.borderColor = "gray";
                        $newPwVal.innerHTML = "";
                        newPwFlag = false;
                    }
                    checkNewPass();
                }); // 리스너끝
                $newPwChk.addEventListener("keyup", (e) => {
                    const pwValue = $newPw.value;
                    const pwValue2 = $newPwChk.value;
                    console.log("비밀번호확인이벤트실행중?");
                    if (pwValue2.length === 0 || !newPwFlag) {
                        $newPwChk.style.borderColor = "gray";
                        $newPwChkVal.innerHTML = "";
                        newPwChkFlag = false;
                    } else {
                        if (pwValue2 !== pwValue) {
                            $newPwChk.style.borderColor = "red";
                            $newPwChkVal.innerHTML =
                                '<b class="warning">[비밀번호가 일치하지 않습니다.]</b>';
                            newPwChkFlag = false;
                        } else {
                            $newPwChk.style.borderColor = "green";
                            $newPwChkVal.innerHTML =
                                '<b class="success">[비밀번호가 일치합니다.]</b>';
                            newPwChkFlag = true;
                        }
                    }
                    checkNewPass();
                }); // 리스너끝

                document
                    .querySelector(".find-pw-submit")
                    .addEventListener("click", async (e) => {
                        const requestInfo = {
                            method: "POST",
                            headers: {
                                "content-type": "application/json",
                            },
                            body: JSON.stringify({
                                email: email,
                                password: $newPw.value,
                            }),
                        };
                        const res = await fetch("/user/change-password", requestInfo);
                        if(res.status == 403) {
                            alert("오류발생");
                            return;
                        }
                        alert("비밀번호 변경이 완료되었습니다.");
                        $dialog.close();
                    });
            }

            const findIdHandler = (e) => {
                $dialog.showModal();
                $modalWrap.innerHTML = `
                <input class="code-input" type="text" placeholder="식별코드">
                <button class="find-submit">이메일 찾기</button>
                `;
                const $btn = $dialog.querySelector(".find-submit");

                $btn.addEventListener("click", submitHandler);
            };
            const findPwHandler = (e) => {
                $dialog.showModal();
                $modalWrap.innerHTML = `
                <input class="code-input" type="text" placeholder="식별코드">
                <input class="find-email-input" type="email" placeholder="이메일">
                <button class="change-submit">비밀번호 찾기</button>
                `;
                const $btn = $dialog.querySelector(".change-submit");

                $btn.addEventListener("click", pwSubmitHandler);
            };

            $dialog.addEventListener("click", (e) => {
                if (e.target.matches("#find-dialog")) $dialog.close();
            });
            $idBtn.addEventListener("click", findIdHandler);
            $pwBtn.addEventListener("click", findPwHandler);
        </script>
    </body>
</html>
