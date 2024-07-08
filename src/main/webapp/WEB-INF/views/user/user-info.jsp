<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <%@ include file="../include/static-head.jsp"%>
        <link rel="stylesheet" href="/assets/css/mypage.css" />
        <link rel="stylesheet" href="/assets/css/header.css" />
    </head>

    <body>
        <header class="main-header">
            <a href="/index">
                <div class="logo" ><img class="main-logo" src="../../assets/img/logo2.png"/></div>
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
        <dialog id="follow-dialog">
            <div class="dialog-wrap">
                <h2 class="user-type"></h2>
                <ul class="users-info"></ul>
            </div>
        </dialog>
        <div class="outer-content-wrap">
            <div class="inner-content-wrap">
                <div id="user-info">
                    <div class="name" data-target-email="${userInfo.email}" >
                        <h1>${userInfo.nickname}
                            <c:choose>
                                <c:when test="${login == null}">

                                </c:when>
                                <c:when test="${wasFollow}">
                                    <span class="follow-btn green">팔로잉</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="follow-btn gray">팔로우</span>
                                </c:otherwise>
                            </c:choose>
                        </h1>
                        <p>${userInfo.email}</p>
                        <p>가입일: ${userInfo.regDate}</p>
                    </div>
                    <div class="user-relation">
                        <a href="#" id="follower">팔로워(${follower})명</a>
                        <div class="wall"></div>
                        <a href="#" id="following">팔로잉(${following})명</a>
                    </div>
                </div>
                <div class="title-btn-wrap">
                    <div class="discuss-btn title-btn">　참여중인 토론(${discussions.size()})</div>
                    <div class="wall"></div>
                    <div class="review-btn title-btn">　 작성한 리뷰(${reviews.size()})　</div>
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
        <footer>
            <div class="footer-wrap">
                <div class="image-wrap">
                    <img class="main-logo" src="../../assets/img/logo2.png"/>
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
            const $content = document.querySelector(".content-wrap");
            const $discussBtn = document.querySelector(".discuss-btn");
            const $reviewBtn = document.querySelector(".review-btn");
            const $disc = document.querySelector(".discuss-content");
            const $review = document.querySelector(".review-contents");
            const $hidden = document.querySelector("#hidden-content");
            const $followBtn = document.querySelector(".follow-btn");
            const infoUserEmail = document.querySelector(".name").dataset.targetEmail;

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
            function followHandler() {
                if ($followBtn.classList.contains("gray")) {
                    fetch(`/user/follow?userEmail=${login.email}&targetEmail=\${infoUserEmail}`);
                    $followBtn.classList.remove("gray");
                    $followBtn.classList.add("green");
                    $followBtn.textContent = "취소";
                } else if($followBtn.classList.contains("green")) {
                    fetch(`/user/unfollow?userEmail=${login.email}&targetEmail=\${infoUserEmail}`);
                    $followBtn.classList.remove("green");
                    $followBtn.classList.add("gray");
                    $followBtn.textContent = "팔로우";
                }
            }

            const render = (dto, flag) => {
                console.log(dto.exist);
                const $li = document.createElement("li");
                $li.dataset.email = dto.email;
                $li.classList.add("user-list");
                $li.innerHTML = `<span class="email">\${dto.nickname}/\${dto.email}</span>`;
                $users.appendChild($li);
            };
            async function followerHandler(e) {
                e.preventDefault();
                $type.textContent = "팔로워";
                $followDialog.showModal();
                console.log("follower click!");
                $users.innerHTML = "";
                const res = await fetch(`/user/user-info/follower/\${infoUserEmail}`);
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
                const res = await fetch(`/user/user-info/following/\${infoUserEmail}`);
                const json = await res.json();
                console.log(json.length);
                $following.textContent = `팔로잉(\${json.length})명`;
                json.forEach((dto) => render(dto, false));
            }
            const btnTextChangeHandler = e => {
                if ($followBtn.classList.contains("gray")) {
                    $followBtn.textContent = "팔로우";
                } else if($followBtn.classList.contains("green")) {   
                    $followBtn.textContent = "취소";
                }
            }
            const btnTextResetHandler = e => {
                if ($followBtn.classList.contains("gray")) {
                    $followBtn.textContent = "팔로우";
                } else if($followBtn.classList.contains("green")) {   
                    $followBtn.textContent = "팔로잉";
                }

            }

            
            
            $followDialog.addEventListener("click", (e) => {
                if (e.target.matches("#follow-dialog")) $followDialog.close();
            });
            $follower.addEventListener("click", followerHandler);
            $following.addEventListener("click", followingHandler);
            $discussBtn.addEventListener("click", discussRenderingHandler);
            $reviewBtn.addEventListener("click", reviewRenderingHandler);
            $followBtn.addEventListener("click", followHandler);
            $followBtn.addEventListener("mouseover", btnTextChangeHandler);
            $followBtn.addEventListener("mouseout", btnTextResetHandler);
        </script>
    </body>
</html>
