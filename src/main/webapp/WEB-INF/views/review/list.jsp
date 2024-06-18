<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <%@ include file="../header.jsp" %> 상위 디렉토리의 header.jsp 포함 -->
<%@ include file="../include/static-head.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Media List</title>
    <!-- CSS 파일 링크 -->
    <link rel="stylesheet" href="/assets/css/reviewListStyle.css">

    <!-- FontAwesome 라이브러리 -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>

    <%@ include file="../include/header.jsp" %>
   
    <main>
        <div class="media-list">
            <c:forEach var="media" items="${mediaList}">
                <div class="media-item" value="${media.mediaNo}">
                    <img src="${media.imageUrl}" alt="${media.mediaTitle}">
                    <h2>${media.mediaTitle}</h2>
                    <p>${media.mediaDesc}</p>
                    <div class="media-overlay" id="media-${media.mediaNo}" style="display: none;">
                        <div class="media-details">
                            <h3>${media.mediaTitle}</h3>
                            <p>작성자: ${media.creator}</p>
                            <p>평점: ${media.rating}</p>
                            <p>등록일: ${#dates.format(media.createdAt, 'yyyy-MM-dd HH:mm:ss')}</p>
                        </div>
                        <div class="reviews">
                            <h3>리뷰</h3>
                            <c:forEach var="review" items="${media.reviews}">
                                <div class="review-item">
                                    <p>${review.content}</p>
                                    <p>작성자: ${review.author}</p>
                                    <p>작성일: ${#dates.format(review.createdAt, 'yyyy-MM-dd HH:mm:ss')}</p>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="review-form">
                            <h3>리뷰 등록</h3>
                            <form action="/review/add" method="post">
                                <input type="hidden" name="mediaNo" value="${media.mediaNo}">
                                <textarea name="content" placeholder="리뷰 내용을 입력하세요..."></textarea>
                                <button type="submit">등록</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

    <script>
        function showMediaDetails(mediaNo) {
            const overlay = document.getElementById(`media-${mediaNo}`);
            const allOverlays = document.querySelectorAll('.media-overlay');

            allOverlays.forEach(o => o.style.display = 'none'); // 모든 오버레이 숨기기
            overlay.style.display = 'block'; // 선택된 미디어의 오버레이만 표시
        }
    </script>
</body>
</html>
