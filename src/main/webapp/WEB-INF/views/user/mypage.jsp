<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <%@ include file="../include/static-head.jsp"%>
        <link rel="stylesheet" href="/assets/css/mypage.css" />
    </head>

    <body>
        <%@ include file="../include/header.jsp" %>
        <dialog id="follow-dialog">
            <div class="dialog-wrap">
                <h2 class="user-type"></h2>
                <ul class="users-info"></ul>
            </div>
        </dialog>
        <div class="outer-content-wrap">
            <div class="inner-content-wrap">
                <div id="user-info">
                    <div class="name">
                        <h1>${login.nickname}</h1>
                        <p>${login.email}</p>
                        <p>가입일: ${login.regDate}</p>
                    </div>
                    <div class="user-relation">
                        <a href="#" id="follower">팔로워(${follower})명</a>
                        <div class="wall"></div>
                        <a href="#" id="following">팔로잉(${following})명</a>
                    </div>

                    <div class="sign-out-wrap">
                        <a href="/user/sign-out">로그아웃</a>
                    </div>

                    <div class="verify-code-wrap">
                        <c:choose>
                            <c:when test="${!login.verified}">
                                <button id="verify">
                                    식별코드 발급받기
                                    <br />(최초1회)
                                </button>
                                <input type="text" id="verify-code" readonly />
                                <button class="copy">COPY</button>
                            </c:when>
                            <c:otherwise>
                                <div>-식별코드 발급 완료-</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="title-btn-wrap">
                    <div class="discuss-btn title-btn">　참여중인 토론(${discussions.size()})</div>
                    <div class="wall"></div>
                    <div class="review-btn title-btn">　 내 리뷰(${reviews.size()}) 　　</div>
                </div>
                <div class="content-wrap"></div>

                <div id="hidden-content">
                    <div class="discuss-content">
                        <c:choose>
                            <c:when test="${discussions.size() == 0}">
                                <h2 class="no-content">참여중인 토론이 없습니다.</h2>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="d" items="${discussions}">
                                    <div class="discuss-card" data-dno="${d.discussionNo}">
                                        <h2 class="discussion-title" onclick="window.location.href='/discussion/detail?dno=${d.discussionNo}'">${d.shortTitle}</h2>
                                        <hr>
                                        <div>
                                            <span class="media-title" onclick="window.location.href='/media/${d.mediaNo}'">
                                                <h3>${d.mediaTitle}</h3>
                                            <img src="${d.imageUrl}" alt="${d.mediaTitle}"/>
                                            </span>
                                            <span class="nickname">작성자: ${d.nickname}</span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="review-contents">
                        <c:choose>
                            <c:when test="${reviews.size() == 0}">
                                <h2 class="no-content">작성한 리뷰가 없습니다.</h2>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="review" items="${reviews}">
                                    <div class="review-card">
                                        <h2 class="media-title"
                                            onclick="window.location.href='/review/list/${review.mediaNo}'"
                                            >작품: ${review.mediaTitle}</h2>
                                        <div class="review-text">
                                            내용: ${review.reviewText}
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                </div>
            </div>
        </div>
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

        <script>
            const $type = document.querySelector(".user-type");
            const $followDialog = document.querySelector("#follow-dialog");
            const $users = document.querySelector(".users-info");
            const $follower = document.querySelector("#follower");
            const $following = document.querySelector("#following");
            const $verify = document.querySelector("#verify");
            const $copy = document.querySelector(".copy");
            const $content = document.querySelector(".content-wrap");
            const $discussBtn = document.querySelector(".discuss-btn");
            const $reviewBtn = document.querySelector(".review-btn");
            const $disc = document.querySelector(".discuss-content");
            const $review = document.querySelector(".review-contents");
            const $hidden = document.querySelector("#hidden-content");

            const discussRenderingHandler = (e) => {
                $discussBtn.classList.add("active");
                $reviewBtn.classList.remove("active");
                $hidden.appendChild($review);
                $content.appendChild($disc);
                
            };
            const reviewRenderingHandler = (e) => {
                $discussBtn.classList.remove("active");
                $reviewBtn.classList.add("active");
                $hidden.appendChild($disc);
                $content.appendChild($review);
                
            };

            const render = (dto, flag) => {
                console.log(dto.exist);
                const $li = document.createElement("li");
                $li.dataset.email = dto.email;
                $li.classList.add("user-list");
                $li.innerHTML = `<span class="email">\${dto.nickname}/\${dto.email}</span>
                        <span>Follow
                        <i title="팔로우하기" class="fas fa-user-check"></i>
                        UnFollow
                        <i title="팔로우 취소하기" class="fas fa-user-times"></i>
                        </span>
                        `;
                const $cancel = $li.querySelector(".fa-user-times");
                const $add = $li.querySelector(".fa-user-check");
                if (!dto.exist) {
                    $cancel.classList.add("cancel");
                    $cancel.classList.remove("done");
                    $add.classList.remove("add");
                    $add.classList.add("done");
                    $add.addEventListener("click", (e) => {
                        fetch(
                            `/user/follow?userEmail=${login.email}&targetEmail=\${dto.email}`
                        );
                        if (flag) {
                            setTimeout(followerHandler, 300);
                        } else {
                            setTimeout(followingHandler, 300);
                        }
                    });
                } else {
                    $cancel.classList.remove("cancel");
                    $cancel.classList.add("done");
                    $add.classList.add("add");
                    $add.classList.remove("done");
                    $cancel.addEventListener("click", (e) => {
                        fetch(
                            `/user/unfollow?userEmail=${login.email}&targetEmail=\${dto.email}`
                        );
                        if (flag) {
                            setTimeout(followerHandler, 300);
                        } else {
                            setTimeout(followingHandler, 300);
                        }
                    });
                }
                $users.appendChild($li);
            };
            async function followerHandler(e) {
                e.preventDefault();
                $type.textContent = "팔로워";
                $followDialog.showModal();
                console.log("follower click!");
                $users.innerHTML = "";
                const res = await fetch(`/user/mypage/follower`);
                const json = await res.json();
                console.log(json.length);
                $follower.textContent = `팔로워(\${json.length})명`;
                const res2 = await fetch(`/user/mypage/following`);
                const json2 = await res2.json();
                $following.textContent = `팔로잉(\${json2.length})명`;
                json.forEach((dto) => render(dto, true));
            }

            async function followingHandler(e) {
                e.preventDefault();
                $type.textContent = "팔로잉 중";
                $followDialog.showModal();
                console.log("following click!");
                $users.innerHTML = "";
                const res = await fetch(`/user/mypage/following`);
                const json = await res.json();
                console.log(json.length);
                $following.textContent = `팔로잉(\${json.length})명`;
                json.forEach((dto) => render(dto, false));
            }

            async function verifyHandler(e) {
                e.preventDefault();
                const $code = document.querySelector("#verify-code");
                const agree = confirm(
                    "식별코드는 최초 1회만 발급받을 수 있습니다.\n(복사 혹은 스크린샷을 통해 저장해주세요)\n 지금 발급 받으시겠습니까?"
                );
                console.log(e.target);
                if (!agree) return;
                const res = await fetch("/user/verify");
                $code.value = await res.text();
            }
            const copyCodeHandler = (e) => {
                const $code = document.querySelector("#verify-code");
                navigator.clipboard.writeText($code.value);
                alert("복사되었습니다.");
            };

            $followDialog.addEventListener("click", (e) => {
                if (e.target.matches("#follow-dialog")) $followDialog.close();
            });
            $follower.addEventListener("click", followerHandler);
            $following.addEventListener("click", followingHandler);
            $verify?.addEventListener("click", verifyHandler);
            $copy?.addEventListener("click", copyCodeHandler);
            $discussBtn.addEventListener("click", discussRenderingHandler);
            $reviewBtn.addEventListener("click", reviewRenderingHandler);
        </script>
    </body>
</html>
