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
        <!-- <div id="backdrop" class="none"></div> -->
        <%@ include file="../include/header.jsp" %>
        <dialog id="follow-dialog">
            <div class="dialog-wrap">
                <h2 class="user-type"></h2>
                <ul class="users-info"></ul>
            </div>
        </dialog>
        <section id="user-info">
            <h2 class="content-name">유저 정보</h2>
            <p>닉네임: ${login.nickname}</p>
            <p>가입일: ${login.regDate}</p>
            <p id="follower">팔로워(${follower})명</p>
            <p id="following">팔로잉(${following})명</p>
            <p>로그아웃</p>
        </section>
        <div class="content-wrap">
            <div class="inner-wrap">
                <h2 class="content-name">내가 참여한 토론</h2>
                <section id="user-discuss" class="content-section">
                    <div class="discuss">
                        <h3>토론 제목 1</h3>
                    </div>
                    <div class="discuss">
                        <h3>토론 제목 2</h3>
                    </div>
                </section>
            </div>

            <div class="inner-wrap">
                <h2 class="content-name">작성한 리뷰</h2>
                <section id="user-reviews" class="content-section">
                    <div class="review">
                        <h3>미디어명</h3>
                        <p>리뷰 제목</p>
                    </div>
                    <div class="review">
                        <h3>미디어명</h3>
                        <p>리뷰 제목</p>
                    </div>
                </section>
            </div>
        </div>

        <%@ include file="../include/footer.jsp" %>

        <script>
            const $type = document.querySelector(".user-type");
            const $followDialog = document.querySelector("#follow-dialog");
            const $users = document.querySelector(".users-info");
            const $follower = document.querySelector("#follower");
            const $following = document.querySelector("#following");

            const render = (dto, flag) => {
                console.log(dto.exist);
                const $li = document.createElement("li");
                $li.dataset.email = dto.email;
                $li.classList.add("user-list");
                $li.innerHTML = `<span class="email">\${dto.email}</span>
                        <span>
                        <i title="팔로우하기" class="fas fa-user-check"></i>
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
            async function followerHandler() {
                $type.textContent = "나를 팔로우한 유저";
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

            async function followingHandler() {
                $type.textContent = "내가 팔로우 중인 유저";
                $followDialog.showModal();
                console.log("following click!");
                $users.innerHTML = "";
                const res = await fetch(`/user/mypage/following`);
                const json = await res.json();
                console.log(json.length);
                $following.textContent = `팔로잉(\${json.length})명`;
                json.forEach((dto) => render(dto, false));
            }
            $followDialog.addEventListener("click", (e) => {
                if (e.target.matches("#follow-dialog")) $followDialog.close();
            });
            $follower.addEventListener("click", followerHandler);
            $following.addEventListener("click", followingHandler);
        </script>
    </body>
</html>
