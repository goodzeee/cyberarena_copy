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
    <!-- FontAwesome 라이브러리 -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
    <%-- 공통 header 부분 include --%>
    <%@ include file="../include/header.jsp"%>

    <main>
        <div id="wrap" class="media-detail" data-mno="${media.mediaNo}">
            <h2>${media.mediaTitle}</h2>
            <div class="media-info">
                <div class="media-image">
                    <img src="${media.imageUrl}" alt="${media.mediaTitle} 이미지">
                </div>
                <div class="media-description">
                    <p><strong>제작자:</strong> ${media.creator}</p>
                    <p><strong>평점:</strong> ${media.rating}</p>
                    <p><strong>설명:</strong> ${media.mediaDesc}</p>
                    <p><strong>등록일:</strong> ${media.createdAt}</p>
                </div>
            </div>

            <hr>

            <!-- 리뷰 등록 폼 -->
            <c:if test="${empty login}">
                   <a href="/user/sign-in?redirect/review/list/${reviews.mediaNo}">리뷰 등록은 로그인 후 사용하실 수 있습니다 😏</a>
            </c:if>

            <c:if test = "${not empty login}">
            <div class="review-form">
                <h3>리뷰 등록</h3>

                <form id="reviewForm" action="/review/add" method="POST">
                    <input type="hidden" id="mediaNo" name="mediaNo" value="${media.mediaNo}">
                    <input type="hidden" id="email" name="email" value="${login.email}">
                    
                    <label for="reviewText">리뷰 내용:</label>
                    <textarea id="reviewText" name="reviewText" placeholder="리뷰 내용을 입력해주세요." rows="4" cols="50" required></textarea><br><br>
                    
                    <label for="nickname">작성자: </label>
                    <input type="text" id="nickname" name="nickname" value="${login.nickname}" readonly>
                    
                                    
                    <label for="userRating">별점:</label>
                    <select id="userRating" name="userRating" required>
                        <c:forEach var="i" begin="1" end="10">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select><br><br>
                    
                    <label for="discussionStatus">토론신청 허용 여부:</label>
                    <input type="checkbox" id="discussionStatus" name="discussionStatus" value="true">
                    <label for="discussionStatus">허용</label><br>
                    
                    <button id="reviewBtn" type="button">등록</button>
                </form>
            </c:if>
            
            </div>

            <!-- 리뷰 목록 영역 -->
            <hr>
            <h3>리뷰 목록</h3>

            <c:set var="totalRating" value="0" />
               <c:set var="reviewCount" value="0" />

               <c:forEach var="review" items="${reviews.reviews}">
                    <c:set var="totalRating" value="${totalRating + review.userRating}"/>
                    <c:set var="reviewCount" value="${reviewCount + 1}"/>
               </c:forEach>

                    <!-- 리뷰가 있을 때만 평균 별점 보여주기 -->
                <c:if test="${reviewCount > 0}">
                    <c:set var="averageRating" value="${totalRating / reviewCount}" />
                    <p class="review-average"><strong>미디어 리뷰 평균 별점:</strong> <fmt:formatNumber value="${averageRating}" type="number" maxFractionDigits="2" /></p>
                </c:if>

            <div class="review-list">

                <c:forEach var="review" items="${reviews.reviews}">

                    <div class="review-item">
                        <p>${review.text}</p>
                        <p><strong>작성자:</strong> ${review.nickname}</p>
                        <p><strong>별점:</strong> ${review.userRating}</p>

                     <!-- 토론 신청 허용 여부 조건에 따라 링크 또는 텍스트로 표시 -->
        <c:choose>
            <c:when test="${review.discussionStatus == 'ALLOW'}">
                <!--href="/discussion/register" 토론 작성 페이지가 바로 뜨면 좋은데 ... -->
                <p><strong>토론신청 허용 여부:</strong> <a href="/discussion/list">ALLOW</a></p>
            </c:when>
            <c:otherwise>
                <p><strong>토론신청 허용 여부:</strong> DISALLOW</p>
            </c:otherwise>
        </c:choose>

                        <p><strong>작성일:</strong> ${review.reviewCreatedAt}</p>

                        <!-- 본인이 쓴글에만 접근할 수 있게 조건 렌더링되도록 -->
                    <c:if test="${login.email == review.email}">
                        <div class="review-actions">
                            <!-- 삭제 버튼 -->
                            <button class="del-btn" onclick="deleteReview(${review.reviewNo})">삭제</button>
                            <!-- <form id="deleteForm_${review.reviewNo}" method="post" action="/review/delete/${review.reviewNo}">
                                <input type="hidden" name="_method" value="DELETE"> -->
                            <!-- <button class="del-btn" onclick="deleteReview(${review.reviewNo})" data-reviewNo="${review.reviewNo}" type="button">삭제</button> -->
                            <!-- </form> -->
                            
                            <!-- 수정 버튼 -->
                            <button onclick="openEditModal(${review.reviewNo}, '${review.text}', ${review.userRating}, '${review.discussionStatus}')">수정</button>
                        </div>
                    </c:if>
                    </div>
                    <hr>
                </c:forEach>
            </div>
        </div>

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
                        <c:forEach var="i" begin="1" end="10">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select><br><br>

                    <label for="editDiscussionStatus">토론신청 허용 여부:</label>
                    <input type="checkbox" id="editDiscussionStatus" name="discussionStatus" value="true">
                    <label for="editDiscussionStatus">허용</label><br>

                    <button type="button" onclick="submitEditForm()">저장</button>
                </form>
            </div>
        </div>

    </main>

    <script>

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

    fetch(`/review/modify`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            reviewNo: reviewNo,
            reviewText: reviewText,
            userRating: userRating,
            discussionStatus: discussionStatus
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

function deleteReview(reviewNo) {
    if (confirm('정말 삭제하시겠습니까?')) {
        fetch(`/review/delete/${reviewNo}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                location.reload(); // 삭제 성공 시 페이지 새로고침
            } else {
                alert('리뷰 삭제에 실패했습니다.');
            }
        })
        .catch(error => console.error('Error:', error));
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


// const delButtons = document.querySelectorAll('.del-btn');

// delButtons.forEach(button => {
//     button.addEventListener('click', function() {
//         const reviewNo = this.getAttribute('data-reviewNo');

//         if (confirm('정말 삭제하시겠습니까?')) {
//             fetch(`/review/delete/${reviewNo}`, {
//                 method: 'DELETE',
//                 headers: {
//                     'Content-Type': 'application/json'
//                 }
//             })
//             .then(response => {
//                 if (response.ok) {
//                     location.reload(); // 삭제 성공 시 페이지 새로고침
//                 } else {
//                     alert('리뷰 삭제에 실패했습니다.');
//                 }
//             })
//             .catch(error => console.error('Error:', error));
//         }
//     });
// });


// document.getElementById('reviewBtn').addEventListener('click', function () {
//     const mediaNo = document.querySelector('input[name="mediaNo"]').value;
//     const reviewText = document.getElementById('reviewText').value;
//     const userRating = document.getElementById('userRating').value;
//     const nickname = document.getElementById('nickname').value;
//     const email = document.getElementById('email').value; // email 값 가져오기
//     const discussionStatus = document.getElementById('discussionStatus').checked ? 'ALLOW' : 'DISALLOW';

//     fetch('/review/add', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify({
//             mediaNo: mediaNo,
//             reviewText: reviewText,
//             userRating: userRating,
//             nickname: nickname,
//             email: email, // email 값 포함
//             discussionStatus: discussionStatus
//         })
//     })
//     .then(response => {
//         if (response.ok) {
//             location.reload();
//         } else {
//             alert('리뷰 등록에 실패했습니다.');
//         }
//     })
//     .catch(error => console.error('Error:', error));
// });


        // 리뷰 등록 폼 submit 이벤트 리스너
    // document.getElementById('reviewBtn').addEventListener('click', function () {
    //     const formData = new FormData(document.getElementById('reviewForm'));

    //     fetch('/review/add', {
    //         method: 'POST',
    //         body: formData
    //     })
    //     .then(response => {
    //         if (response.ok) {
    //             return response.json(); // 응답 JSON 데이터 파싱
    //         } else {
    //             throw new Error('리뷰 등록에 실패했습니다.');
    //         }
    //     })
    //     .then(data => {
    //         // 등록 성공 시 리뷰 목록에 추가
    //         appendReviewToList(data);
    //         // 입력 필드 초기화
    //         document.getElementById('reviewText').value = '';
    //         document.getElementById('userRating').value = '1';
    //         document.getElementById('discussionStatus').checked = false;
    //     })
    //     .catch(error => {
    //         alert(error.message);
    //         console.error('Error:', error);
    //     });
    // });

    // // 리뷰 목록에 리뷰 추가 함수
    // function appendReviewToList(review) {
    //     const reviewList = document.querySelector('.review-list');

    //     const reviewItem = document.createElement('div');
    //     reviewItem.classList.add('review-item');

    //     reviewItem.innerHTML = `
    //         <p>${review.reviewText}</p>
    //         <p><strong>작성자:</strong> ${review.nickname}</p>
    //         <p><strong>별점:</strong> ${review.userRating}</p>
    //         <p><strong>토론신청 허용 여부:</strong> ${review.discussionStatus ? '허용' : '비허용'}</p>
    //         <p><strong>작성일:</strong> ${review.reviewCreatedAt}</p>

    //         <div class="review-actions">
    //             <form id="deleteForm_${review.reviewNo}" method="post" action="/review/delete/${review.reviewNo}">
    //                 <button type="button" onclick="deleteReview(${review.reviewNo});">삭제</button>
    //             </form>
    //             <button onclick="openEditModal(${review.reviewNo}, '${review.reviewText}', ${review.userRating}, '${review.discussionStatus}')">수정</button>
    //         </div>
    //         <hr>
    //     `;

    //     reviewList.prepend(reviewItem); // 최신 리뷰를 위로 추가
    // }

    //     // 리뷰 삭제 함수
    //     function deleteReview(reviewNo) {
    //         if (confirm('정말 삭제하시겠습니까?')) {
    //             fetch(`/review/delete/${reviewNo}`, {
    //                 method: 'DELETE'
    //             })
    //             .then(response => {
    //                 if (response.ok) {
    //                     location.reload(); // 삭제 성공 시 페이지 새로고침
    //                 } else {
    //                     alert('리뷰 삭제에 실패했습니다.');
    //                 }
    //             })
    //             .catch(error => console.error('Error:', error));
    //         }
    //     }

    //     // 리뷰 수정 모달 열기 함수
    //     function openEditModal(reviewNo, reviewText, userRating, discussionStatus) {
    //         document.getElementById('editReviewNo').value = reviewNo;
    //         document.getElementById('editReviewText').value = reviewText;
    //         document.getElementById('editUserRating').value = userRating;
    //         document.getElementById('editDiscussionStatus').checked = discussionStatus === 'ALLOW';
    //         document.getElementById('editModal').style.display = 'block';
    //     }

    //     // 리뷰 수정 모달 닫기 함수
    //     function closeEditModal() {
    //         document.getElementById('editModal').style.display = 'none';
    //     }

    //     // 리뷰 수정 폼 제출 함수
    //     function submitEditForm() {
    //         const reviewNo = document.getElementById('editReviewNo').value;
    //         const reviewText = document.getElementById('editReviewText').value;
    //         const userRating = document.getElementById('editUserRating').value;
    //         const discussionStatus = document.getElementById('editDiscussionStatus').checked ? 'ALLOW' : 'DISALLOW';

    //         fetch(`/review/modify`, {
    //             method: 'POST', // 수정은 POST로 전송
    //             headers: {
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify({
    //                 reviewNo: reviewNo,
    //                 reviewText: reviewText,
    //                 userRating: userRating,
    //                 discussionStatus: discussionStatus
    //             })
    //         })
    //         .then(response => {
    //             if (response.ok) {
    //                 closeEditModal(); // 수정 저장 성공 시 모달창 닫기
    //                 location.reload(); // 페이지 새로고침
    //             } else {
    //                 alert('리뷰 수정에 실패했습니다.');
    //             }
    //         })
    //         .catch(error => console.error('Error:', error));
    //     }
    </script>

</body>
</html>
