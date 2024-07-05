<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/kibeom/detail.css">
</head>
<body>

<div id="backdrop" class="hide"></div>

<%@ include file="../include/header.jsp" %>
<div id="whole">
    <aside id="left-aside">
        <div class="aside-wrap">
            <h2 class="aside-header">지금 뜨는 토론 <i class="live-icon">LIVE</i></h2>
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
    <div class="content-wrap">

        <div class="discussion-card">

            <div class="discussion-header">
                <h1 class="discussion-title">${found.discussionTitle}</h1>
                <button
                        class="list-btn"
                        type="button"
                        onclick="window.location.href='${ref}'"
                >
                    목록
                </button>
            </div>
            <div class="discussion-body">
                <span class="discussion-date">${found.formattedDiscussionCreatedAt}</span>

                <c:if test="${login.nickname == found.nickname}">
                    <button class="del-btn" data-href="/discussion/remove?dno=${found.discussionNo}">삭제</button>
                </c:if>

            </div>
            <div class="discussion-nickname nickname" data-email="${found.email}">작성자: ${found.nickname}</div>
            <div class="discussion-media-name" data-media-no="${found.mediaNo}">미디어: ${found.mediaTitle}</div>


            <br>
            <div class="discussion-offer">${found.discussionOffer}</div>
        </div>
        <div class="comment-section">
<%--            <h2>댓글 [${count}]</h2>--%>
            <c:if test="${login != null}">
                <div class="comment-form">
                    <form id="commentForm">
                        <textarea id="reply-textarea" placeholder="댓글을 입력하세요... &#13;&#10; 타인에 대한 비방, 욕설, 성적인 발언 시 관리자 검토 하에 제한조치가 이뤄질 수 있습니다."
                                  name="discussionReplyContent"></textarea>
                        <input type="hidden" value="${found.discussionNo}" name="discussionNo">
                        <input type="hidden" value="${login.email}" name="email">
                        <button type="button" id="submitComment" class="submit-comment">등록</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${login == null}">
                <div class="comment-form">
                    <div class="message">댓글 작성은 로그인 후 가능합니다.</div>
                    <div class="button-wrap">
                        <button onclick="window.location.href = `/user/sign-in`">로그인</button>
                    </div>
                </div>
            </c:if>
            <div id="comments">
                <!-- 댓글 목록 -->
            </div>
        </div>
    </div>
    <aside id="right-aside">
        <div class="aside-wrap">
            <h2 class="aside-header"> 🔥 최근 인기 리뷰 🔥</h2>
            <ul>
                <li>#1 정처기 재밌음? </li>
                <li>#2 타짜를 보고 왔는데..</li>
                <li>#3 요즘 영화값 비싸요</li>
                <li>#4 1987 보고 오신분!!</li>
                <li>#5 명량에 대해..</li>
                <li>#6 정처기에 대해..</li>
                <li>#7 실기에 대해..</li>
            </ul>

        </div>
    </aside>
</div>

<%@ include file="../include/footer.jsp" %>

<script type="module" src="/assets/js/kibeom/getDiscussReply.js"></script>
<script type="module" src="/assets/js/kibeom/detail.js"></script>
<script>

    const $finishBtn = document.getElementById('finish');
    $finishBtn?.addEventListener('click', e => {
        window.location.href = "/discussion/modify"
    })

    document.querySelector("#whole").addEventListener("click", e => {
        console.log("일단 눌렀을 경우")
        if(e.target.matches(".nickname")) {
            const email = e.target.dataset.email;
            window.location.href= `/user/user-info/\${email}`;
            return;
        }
        if(e.target.matches(".discussion-media-name")) {
            const mediaNo = e.target.dataset.mediaNo;
            window.location.href = `/review/list/\${mediaNo}`;
            return;
        }
        console.log("안눌렸을 경우");
    })


</script>
</body>
</html>