<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp"%>
    <meta charset="UTF-8">
    <title>리뷰 관리</title>
    <!-- CSS 파일 링크 -->
    <link rel="stylesheet" href="/assets/css/reviewListStyle.css">
    <!-- FontAwesome 라이브러리 -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
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
            <div class="review-form">
                <h3>리뷰 등록</h3>
                <!-- <form action="/review/add" method="post"> -->
                <input type="hidden" name="mediaNo" value="${media.mediaNo}">
                <label for="reviewText">리뷰 내용:</label>
                <textarea id="reviewText" name="reviewText" placeholder="리뷰 내용을 입력해주세요." rows="4" cols="50" required></textarea><br><br>

                <label for="userRating">별점:</label>
                <select id="userRating" name="userRating" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select><br><br>

                <label for="discussionStatus">토론신청 허용 여부:</label>
                <input type="checkbox" id="discussionStatus" name="discussionStatus" value="true">
                <label for="discussionStatus">허용</label><br>

                <button id="reviewBtn" type="submit">등록</button>
                <!-- </form> -->
            </div>

            <!-- 리뷰 목록 영역 -->
            <hr>
            <h3>리뷰 목록</h3>
            <div class="review-list">
                <c:forEach var="review" items="${reviews}">
                    <div class="review-item">
                        <p>${review.text}</p>
                        <p><strong>작성자:</strong> ${review.email}</p>
                        <p><strong>별점:</strong> ${review.userRating}</p>
                        <p><strong>토론신청 허용 여부:</strong> ${review.discussionStatus}</p>
                        <p><strong>작성일:</strong> ${review.reviewCreatedAt}</p>
                        
                        <div class="review-actions">
                            <!-- 삭제 버튼 -->
                            <form id="deleteForm_${review.reviewNo}">
                                <button type="button" onclick="deleteReview(${review.reviewNo});">삭제</button>
                            </form>
                            <!-- 수정 버튼 -->
                            <button onclick="openEditModal(${review.reviewNo}, '${review.text}', ${review.userRating}, '${review.discussionStatus}')">수정</button>
                        </div>
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
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
            </select><br><br>
            <label for="editDiscussionStatus">토론신청 허용 여부:</label>
            <input type="checkbox" id="editDiscussionStatus" name="discussionStatus" value="true">
            <label for="editDiscussionStatus">허용</label><br>
            <button type="button" onclick="submitEditForm()">저장</button>
        </form>
    </div>
</div>

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

function deleteReview(rno) {
    if (confirm('정말 삭제하시겠습니까?')) {
        const deleteForm = document.getElementById('deleteForm_' + rno);
        deleteForm.submit();
    }
}

document.getElementById('reviewBtn').addEventListener('click', function () {
    const mediaNo = document.querySelector('input[name="mediaNo"]').value;
    const reviewText = document.getElementById('reviewText').value;
    const userRating = document.getElementById('userRating').value;
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

            </script>
</body>
</html>
