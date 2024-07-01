<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>

    <link rel="stylesheet" href="/assets/css/kibeom/discussion-list.css">

    <%-- 페이징 라이브러리 추가 x --%>
</head>

<body>
<div id="backdrop" class="hide"></div>
<%@ include file="../include/header.jsp" %>

<script type="text/javascript">
    const isLoggedIn = ${login != null};
</script>

<div class="list-wrap">
    <h1>토론 리스트</h1>

    <div id="top-parents">
        <button class="make-discussion">토론 생성하기</button>
        <div>
            <form action="/discussion/list" method="get">

                <select class="form-select" name="type" id="search-type">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="writer">작성자</option>
                    <option value="tc">제목+내용</option>
                </select>

                <input type="text" name="keyword" value="${s.keyword}">

                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i>
                </button>

            </form>
        </div>
    </div>


    <div id="whole-wrap">
        <c:if test="${dList.size() == 0}">
            <div class="empty">
                게시물이 존재하지 않습니다.
            </div>
        </c:if>


        <div id="discuss-wrap">
            <c:if test="${dList.size() > 0}">
            <c:forEach var="d" items="${dList}">
                <div class="card" data-dno="${d.discussionNo}">
                    <p class="media-title">미디어: ${d.mediaTitle}</p>
                    <p class="discussion-title">토론 주제: ${d.discussionTitle}</p>
                    <p class="nickname">글쓴이: ${d.nickname}</p>
<%--                    <p class="discussion-offer">세부내용: ${d.discussionOffer}</p>--%>
                    <p class="discuss-view-count">조회수: ${d.viewCount}</p>
                    <p class="reply-count">참여[${d.replyCount}]</p>
                    <p class="discussion-created-at">${d.formattedDiscussionCreatedAt}</p>
                </div>
            </c:forEach>
            </c:if>
        </div>

    </div>

</div>

<!-- 게시글 목록 하단 영역 -->
<div class="bottom-section">

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:if test="${maker.pageInfo.pageNo != 1}">
                <li class="page-item">
                    <a class="page-link" href="/discussion/list?pageNo=1" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="i" begin="${maker.begin}" end="${maker.end}">
                <li class="page-item items" data-page-num="${i}">
                    <a class="page-link" href="/discussion/list?pageNo=${i}&type=${s.type}&keyword=${s.keyword}">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${maker.pageInfo.pageNo != maker.finalPage}">
                <li class="page-item">
                    <a class="page-link" href="/discussion/list?pageNo=${maker.finalPage}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>

</div>
<!-- end div.bottom-section -->


<%@ include file="../include/footer.jsp" %>

<script type="module" src="/assets/js/kibeom/getMedia.js"></script>
<script type="text/javascript" src="/assets/js/kibeom/list.js"></script>
<script>

    function appendActivePage() {
        const currentPage = `${maker.pageInfo.pageNo}`; // 템플릿 리터럴 올바르게 사용

        const $li = document.querySelector(`.page-item[data-page-num="\${currentPage}"]`);

        if ($li) {
            $li.classList.add('active');
        }
    }

    function fixSearchOption () {
        const type =`${s.type}`
        const $option = document.querySelector(`#search-type option[value='\${type}']`);
        $option?.setAttribute('selected', 'selected');

    }


    appendActivePage();
    fixSearchOption();
</script>

</body>
</html>