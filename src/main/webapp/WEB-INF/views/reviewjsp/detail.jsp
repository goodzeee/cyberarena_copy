<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 공통 head 부분 include --%>
    <%@ include file="../include/static-head.jsp"%>
    <meta charset="UTF-8">
    <title>리뷰 관리</title>
    <!-- CSS 파일 링크 -->
    <link rel="stylesheet" href="/assets/css/reviewListStyle.css">
        <link rel="stylesheet" href="/assets/css/header.css" />
    <!-- FontAwesome 라이브러리 -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
    <%-- 공통 header 부분 include --%>
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
<div id="whole">
    <aside id="left-aside">
        <div class="aside-wrap">
            <h2 class="aside-header"><i class="live-icon">LIVE</i><br>지금 뜨는 토론 </h2>
            <ul class="aside-ul">
                <p class="aside-p" onclick="window.location.href=`/discussion/list`">더보기</p>
                <c:forEach var="a" items="${aList}" >
                    <li data-dno="${a.discussionNo}" class="aside-li">
                        <p onclick="window.location.href=`/discussion/detail?dno=${a.discussionNo}`">
                                <%--                            <${status.index + 1}> <span class="aside-title">${a.discussionTitle}</span><br>--%>
                            <span class="aside-title">${a.discussionTitle}</span><br>
                            <i class="fas fa-eye"></i> ${a.viewCount}
                        </p>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </aside>


    <main class="content-content">
        <div id="wrap" class="media-detail" data-mno="${media.mediaNo}">

            <div class="media-info">
                <div class="media-image">
                    <img src="${media.imageUrl}" alt="${media.mediaTitle} 이미지" >
                </div>
                <div class="media-description">
                    <p><strong class="media-title">${media.mediaTitle}</strong></p>
                    <div class="media-box">
                    <p><strong>제작자:</strong> ${media.creator}</p>
                    <p><strong>평점:</strong> ${media.rating}점</p>
                    <p><strong>설명:</strong> ${media.mediaDesc}</p>
                    <p><strong>등록일:</strong> ${media.createdAt}년</p>
                    </div>
                </div>
                <!-- <button id="list-btn" style="margin-top: auto;"><a href="/media/movie"></a>목록</button> -->
            </div>

            <!-- 리뷰 등록 폼 -->
            <c:if test="${empty login}">
                   <a href="/user/sign-in?redirect=/review/list/${reviews.mediaNo}"><p class="login-info">리뷰 기능은 로그인 후 사용하실 수 있습니다 😏</p></a>
            </c:if>

            <c:if test = "${not empty login}">
            <div class="review-form">
                <h3>리뷰 등록</h3>

                <form id="reviewForm" action="/review/add" method="POST">
                    <input type="hidden" id="mediaNo" name="mediaNo" value="${media.mediaNo}">
                    <input type="hidden" id="email" name="email" value="${login.email}">
                    
                    <label for="reviewText">리뷰 내용:</label>
                    <textarea id="reviewText" name="reviewText" placeholder="리뷰 내용을 입력해주세요." rows="4" cols="50" required></textarea><br>
                    
                    <label for="nickname">작성자: </label>
                    <input type="text" id="nickname" name="nickname" value="${login.nickname}" disabled>
                    
                                    
                    <label for="userRating">별점:</label>
                    <select id="userRating" name="userRating" required>
                        <c:forEach var="i" begin="1" end="5">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    
                    <label for="discussionStatus">토론신청 허용 여부:</label>
                    <p for="discussionStatus" style="display: inline;">허용 <input type="checkbox" id="discussionStatus" name="discussionStatus" value="true"></p>
                    
                    <button id="reviewBtn" type="button">등록</button>
                </form>
            

            </div>

            <!-- 리뷰 목록 영역 -->
            <div>
                <h3>리뷰 목록</h3>
                <p class="review-average"><strong>(미디어 리뷰 평점: </strong>${media.rating} / 5)</p>
            </div>
                <c:forEach var="review" items="${reviews.reviews}">
                     <div class="review-list" data-rno="${review.reviewNo}" data-mno="${review.mediaNo}">
                     <div class="review-item">

                        <p class="name"><strong></strong> <span class="nickname" data-email="${review.email}">${review.nickname}</span></p>

                        <p class="star"><strong>⭐</strong> ${review.userRating}</p>
                
        <div class="buttons" style="float: right; padding: 4px;">
            <div class="reaction-buttons">
                <button class="like-btn ${review.userReaction == 'like' ? 'active' : '' }" data-liked="${review.userReaction == 'like'}" data-rno="${review.reviewNo}">
                    <i class="fas fa-thumbs-up"></i>like
                    <span id="like-count">${review.likeCount}</span>
                </button>
            </div>
        </div>

        <hr>

                     <p id="reviewText">${review.text}</p>  <br><br>

                     <!-- 토론 신청 허용 여부 조건에 따라 링크 또는 텍스트로 표시 -->
        <c:choose>
            <c:when test="${review.discussionStatus == 'ALLOW'}">
                <!--href="/discussion/register" 토론 작성 페이지가 바로 뜨면 좋은데 ... -->
                <p><strong>토론신청 허용 여부:</strong> <a href="/discussion/register?reviewNo=${review.reviewNo}">ALLOW</a></p>
            </c:when>
            <c:otherwise>
                <p><strong>토론신청 허용 여부:</strong> DISALLOW</p>
            </c:otherwise>
        </c:choose>

        <p class="review-date"><strong></strong> ${review.reviewCreatedAt}</p>

                        <!-- 본인이 쓴글에만 접근할 수 있게 조건 렌더링되도록 -->
                    <c:if test="${login.email == review.email}">
                        <div class="review-actions">
                            <button class="del-btn" onclick="deleteReview('${review.reviewNo}', '${review.mediaNo}')">삭제</button>
                            <button class="mod-btn" onclick="openEditModal('${review.reviewNo}', '${review.text}', '${review.userRating}', '${review.discussionStatus}')">수정</button>
                        </div>
                    </c:if>

                    <div class="bottom-section">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination pagination-lg pagination-custom">
                                <c:forEach var="i" begin="1" end="${pageMaker.finalPage}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="/review/list/${media.mediaNo}?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>

                </div>
                </div>
                </c:forEach>
                </c:if>

            <!-- </div> -->
        <!-- </div> -->

        <!-- 리뷰 수정 모달 -->
        <div id="editModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeEditModal()">&times;</span>
                <h2>리뷰 수정</h2>
                <form id="editForm">
                    <input type="hidden" id="editReviewNo" name="reviewNo">
                    <label for="editReviewText">리뷰 내용:</label>
                    <textarea id="editReviewText" name="reviewText" rows="4" cols="50" required></textarea><br><br>

                    <label for="editUserRating">별점:</label>
                    <select id="editUserRating" name="userRating" required>
                        <c:forEach var="i" begin="1" end="5">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select><br><br>

                    <label for="editDiscussionStatus">토론신청 허용 여부:</label>
                    <input type="checkbox" id="editDiscussionStatus" name="discussionStatus" value="true">
                    <label for="editDiscussionStatus">허용</label><br>

                    <button id="modBtn" type="button" onclick="submitEditForm()">저장</button>
                </form>
            </div>
        </div>

    </main>

    <aside id="right-aside">
        <div class="aside-wrap">
            <h2 class="aside-header"><i class="live-icon">LIVE</i> <br>지금 뜨는 작품 </h2>
            <ul class="aside-ul">
                <hr>
                <%--                <p class="aside-p" onclick="window.location.href=`/discussion/list`">더보기</p>--%>
                <c:forEach var="m" items="${mList}" >
                    <li class="aside-li" onclick="window.location.href=`/review/list/${m.mediaNo}`">
                        <div class="aside-div">
                            <div>
                                <img src="${m.imageUrl}">
                            </div>
                            <div class="aside-div-right">
                                <h3>${m.mediaTitle}</h3>
                                <p>
                                    <c:choose>
                                        <c:when test="${m.categoryNo == 1}">
                                            영화
                                        </c:when>
                                        <c:when test="${m.categoryNo == 2}">
                                            시리즈
                                        </c:when>
                                        <c:when test="${m.categoryNo == 3}">
                                            도서
                                        </c:when>
                                        <c:otherwise>
                                            기타
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p>${m.rating}</p>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </aside>

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

        // 서버에 좋아요 요청 보내는 함수
async function sendReaction(like, rno, button) {

    const res = await fetch(`/review/like?rno=\${rno}`);
    // const data = await res.json();

    // 응답 데이터에서 likeCount와 userReaction 추출
    const { likeCount, userReaction } = await res.json();

    // 리뷰의 부모 요소에서 해당 리뷰의 like-count 요소 찾아서 개별 리뷰글마다 처리
    const reviewElement = button.closest('.review-list');
    const likeCountElement = reviewElement.querySelector('#like-count');

    // 해당 리뷰의 like-count 업데이트
    likeCountElement.textContent = likeCount;

    // 버튼 활성화 스타일 처리
    updateReactionButtons(button, userReaction);
}

    // 좋아요 버튼 상태에 따라 배경색 변경
    function updateReactionButtons(button, userReaction) {
    console.log('Updating button state:', { button, userReaction });

    // 좋아요 버튼이 눌렀을 경우
    if (userReaction === 'like') {
        button.classList.add('active');
        button.dataset.liked = 'true'; // 데이터 속성 업데이트
        console.log('Button is active:', button);
    } else {
        // 좋아요 버튼이 눌리지 않은 경우
        button.classList.remove('active');
        button.dataset.liked = 'false'; // 데이터 속성 업데이트
        console.log('Button is not active:', button);
    }
}

    // 모든 좋아요 버튼에 대해 클릭 이벤트 주기
   document.querySelectorAll('.like-btn').forEach(button => {
   button.addEventListener('click', e => {
       const reviewElement = e.target.closest('.review-list');
       const reviewNo = reviewElement.dataset.rno;
    //    console.log('click: ', {reviewElement, reviewNo});
       sendReaction('like', reviewNo, button);
   });
});


function openEditModal(reviewNo, reviewText, userRating, discussionStatus) {
    document.getElementById('editReviewNo').value = reviewNo;
    document.getElementById('editReviewText').value = reviewText;
    document.getElementById('editUserRating').value = userRating;
    document.getElementById('editDiscussionStatus').checked = discussionStatus === 'ALLOW';
    document.getElementById('editModal').style.display = 'block';
}

function closeEditModal() {
    document.getElementById('editModal').style.display = 'none';
}

function submitEditForm() {
    const reviewNo = document.getElementById('editReviewNo').value;
    const reviewText = document.getElementById('editReviewText').value;
    const userRating = document.getElementById('editUserRating').value;
    const discussionStatus = document.getElementById('editDiscussionStatus').checked ? 'ALLOW' : 'DISALLOW';
    const mediaNo = document.querySelector(".media-detail").dataset.mno;
    fetch(`/review/modify`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            reviewNo: reviewNo,
            reviewText: reviewText,
            userRating: userRating,
            discussionStatus: discussionStatus,
            mno: mediaNo
        })
    })
    .then(response => {
        if (response.ok) {
            closeEditModal();
            location.reload();
        } else {
            alert('리뷰 수정에 실패했습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
}

// 리뷰 삭제 함수
function deleteReview(reviewNo, mno) {
    console.log(reviewNo, mno);
    if (!reviewNo || !mno) {
        alert('삭제할 리뷰 번호 또는 미디어 번호가 잘못되었습니다.');
        return;
    }
    if (confirm('정말 삭제하시겠습니까?')) {
        // GET 요청을 URL을 통해 보냅니다. `/review/delete/${mno}?reviewNo=${reviewNo}`
        window.location.href = `/review/delete/\${mno}?reviewNo=\${reviewNo}`;
    }
}


document.getElementById('reviewBtn').addEventListener('click', function () {
    const mediaNo = document.querySelector('input[name="mediaNo"]').value;
    const reviewText = document.getElementById('reviewText').value;
    const userRating = document.getElementById('userRating').value;
    const nickname = document.getElementById('nickname').value;
    const email = document.getElementById('email').value; // email 값 가져오기
    const discussionStatus = document.getElementById('discussionStatus').checked ? 'ALLOW' : 'DISALLOW';

    fetch('/review/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            mediaNo: mediaNo,
            reviewText: reviewText,
            userRating: userRating,
            nickname: nickname,
            email: email, // email 값 포함
            discussionStatus: discussionStatus
        })
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert('리뷰 등록에 실패했습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
});

document.querySelector(".content-content").addEventListener("click", e => {
    if(!e.target.matches(".nickname")) return;
    const email = e.target.dataset.email;
    window.location.href=`/user/user-info/\${email}`;
})

</script>

</body>
</html>
