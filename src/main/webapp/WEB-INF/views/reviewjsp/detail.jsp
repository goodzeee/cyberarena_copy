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
                        <p><strong>작성일:</strong>${review.reviewCreatedAt}</p>
                        
                        <div class="review-actions">
                            <form id="deleteForm_${review.rno}">
                                <button type="button" onclick="deleteReview(${review.rno});">삭제</button>
                            </form>
                            <button onclick="location.href='/review/${review.rno}/edit'">수정</button>
                        </div>
                    </div>
                    <hr>
                </c:forEach>
            </div>
            
            <script>

                function deleteReview(rno) {
            if (confirm('정말 삭제하시겠습니까?')) {
                const deleteForm = document.getElementById('deleteForm_' + rno);
                deleteForm.submit();
            }
        }

        // 리뷰 등록 버튼 클릭 시 처리
        document.getElementById('reviewBtn').addEventListener('click', function () {
            const mediaNo = document.querySelector('input[name="mediaNo"]').value;
            const reviewText = document.getElementById('reviewText').value;
            const userRating = document.getElementById('userRating').value;
            const discussionStatus = document.getElementById('discussionStatus').checked ? 'ALLOW' : 'DISALLOW';
    
            // 서버로 리뷰 데이터를 전송하는 AJAX 요청
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
                    // 리뷰 목록을 갱신하거나 페이지를 리로드
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
