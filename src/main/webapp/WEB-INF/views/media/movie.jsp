<%@ page contentType="text/html; charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <%@ include file="../include/static-head.jsp" %>
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@2.0/nanumsquare.css"
    />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link
      rel="stylesheet"
      href="https://unpkg.com/swiper@8/swiper-bundle.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link rel="stylesheet" href="/assets/css/media.css" />
  </head>
  <body>
    <div id="backdrop" class="none"></div>
    <!-- header -->
    <%@ include file="../include/header.jsp" %>
    <main>
      <!-- banner -->

      <div class="banner">
        <!-- 큰 이미지 추천 -->
        <div class="row">
          <div class="row_title">화제의 토론</div>
          <div class="row_posters">
            <div class="rank">

              <svg
              width="200px"
              height="200px"
              viewBox="0 0 70 154"
            >
              <path
                stroke="#ffd700"
                stroke-linejoin="square"
                stroke-width="4"
                d="M35.377 152H72V2.538L2 19.362v30.341l33.377-8.459V152z"
              ></path>
            </svg>

            <svg
            width="200px"
            height="200px"
            viewBox="30 0 80 154"
          >
            <path
              stroke="silver"
              stroke-linejoin="square"
              stroke-width="4"
              d="M3.72 152H113v-30.174H50.484l4.355-3.55 29.453-24.012c5.088-4.124 9.748-8.459 13.983-13.004 4.16-4.464 7.481-9.339 9.972-14.629 2.449-5.203 3.678-11.113 3.678-17.749 0-9.428-2.294-17.627-6.875-24.645-4.597-7.042-10.941-12.494-19.07-16.376C77.803 3.957 68.496 2 58.036 2 47.591 2 38.37 4.023 30.347 8.06c-8.015 4.032-14.457 9.578-19.352 16.654-4.492 6.493-7.389 13.803-8.693 21.952h34.055c1.236-3.52 3.398-6.52 6.459-8.97 3.54-2.834 8.277-4.224 14.147-4.224 5.93 0 10.552 1.537 13.76 4.681 3.181 3.12 4.791 7.024 4.791 11.594 0 4.151-1.16 7.934-3.468 11.298-2.192 3.194-5.987 7.124-11.405 11.84L3.72 122.465V152z"
            ></path>
          </svg>

          <svg
              width="200px"
              height="200px"
              viewBox="10 0 80 154"
            >
              <path
                stroke="#aa8e74"
                stroke-linejoin="square"
                stroke-width="4"
                d="M3.809 41.577h33.243c1.3-2.702 3.545-4.947 6.674-6.72 3.554-2.015 7.83-3.01 12.798-3.01 5.555 0 10.14 1.11 13.723 3.376 3.839 2.427 5.782 6.283 5.782 11.315 0 4.553-1.853 8.395-5.473 11.38-3.547 2.926-8.18 4.37-13.821 4.37H41.44v28.366h16.77c5.572 0 10.275 1.227 14.068 3.711 4.02 2.633 6.071 6.581 6.071 11.616 0 5.705-1.943 9.975-5.853 12.562-3.658 2.42-8.292 3.61-13.863 3.61-5.205 0-9.82-.94-13.827-2.836-3.698-1.75-6.32-4.272-7.785-7.529H2.33c2.096 12.089 7.761 21.65 17.028 28.78C29.242 148.175 42.594 152 59.476 152c10.706 0 20.175-1.783 28.42-5.337 8.185-3.528 14.575-8.486 19.208-14.884 4.595-6.346 6.896-13.938 6.896-22.837 0-6.952-1.93-13.494-5.81-19.666-3.815-6.07-9.68-10.367-17.683-12.908l-5.46-1.735 5.353-2.04c6.659-2.538 11.667-6.338 15.083-11.412 3.431-5.096 5.142-10.806 5.142-17.181 0-8.471-2.262-15.778-6.787-21.985-4.574-6.275-10.7-11.17-18.408-14.696C77.683 3.775 69.109 2 59.687 2 44.084 2 31.515 5.816 21.91 13.415c-9 7.119-15.025 16.486-18.101 28.162z"
              ></path>
            </svg>

            </div>
            <c:forEach var="discussions" items="${discussions}">
            <div class="poster_wrap">
              <section data-view-count="${discussions.viewCount}">
                <h2>${discussions.discussionTitle}</h2>
                <div class="poster_info">
                  <span>${discussions.mediaTitle}</span>
                  <p>${discussions.format}</p>
                </div>
                <div class="poster_count">
                  <span class="count01"><i class="fa-solid fa-user"></i>${discussions.replyCount}명 참여</span>
                  <span>조회수:${discussions.viewCount}</span>
                </div>
              </section>
            </div>
            </c:forEach>
           

          </div>
        </div>
      </div>

      <!-- 작은이미지 추천 -->
      <div class="row">
        <div class="row_title">지금 뜨는 리뷰</div>
        <div class="prev">
            <i class="fa-solid fa-angle-right prev-arrow"></i>
          </div>
        <div class="row_posters slider">
            <c:forEach var="review" items="${reviews}">
              <div class="card-wrapper ">
                <section class="card" data-review-no="${review.reviewNo}">
                  <div class="card-title-wrapper">
                    <h2 class="card-title">${review.mediaTitle}</h2>
                    <div class="time-view-wrapper">
                      <div class="nickname">${review.nickname}</div>
                      <div
                        class="rating"
                        data-rating="${review.userRating}"
                      ></div>
                    </div>
                    <div class="card-content"><img src="${review.imageUrl}" alt="Media Image">  
                      ${review.reviewText}
                  </div>
                </section>
              </div>
              <!-- end div.card-wrapper -->
            </c:forEach>
        </div>  
        <div class="next">
            <i class="fa-solid fa-angle-right"></i>
          </div>
      </div>

      
      <!-- 작은이미지 추천 -->
      <div class="row">
        <div class="row_title">영화 목록</div>
        <div class="prev">
          <i class="fa-solid fa-angle-right prev-arrow"></i>
        </div>
        <div class="row_posters slider">
          <c:forEach items="${imageUrls}" var="imageUrl">
            <div class="media_list">
              <img
              class="img_list"
              src="${imageUrl.imageUrl}"
              alt="Media Image">
              ${imageUrl.mediaTitle}
            </div>
          </c:forEach>
          
        </div>
        <div class="next"><i class="fa-solid fa-angle-right"></i></div>
      </div>
    </main>

    <!--모달 -->
    <div class="modal-wrap none">
      <div class="img-wrap">
        <img
          src="data:image/jpeg;base64,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"
          alt="미디어 이미지"
        />
      </div>
      <div class="media-info">
        <h2>더 글로리</h2>
        <div class="detail-info">
          <span class="rating">평점: 3.3</span>
          <span>2023</span>
          <span>장르 : 스릴러</span>
        </div>
        <div class="description">
          더 글로리 - 위키백과, 우리 모두의 백과사전 《더 글로리》 (영어: The
          Glory)는 넷플릭스에서 2022년 12월 30일부터 방영된 대한민국의 드라마로
          학교폭력에 대한 복수를 다룬 복수극, 범죄극, 스릴러물이다. 송혜교,
          이도현, 임지연, 염혜란, 박성훈, 정성일이 앙상블 캐스팅을 마무리한다.
        </div>
      </div>
      <div class="modal-close">닫기</div>
      <div class="reviews">리뷰</div>
    </div>

    <!-- 모달 끝 -->

    <!-- footer -->
    <%@ include file="../include/footer.jsp" %>
  </body>
  <script>
    // 메뉴 버튼
    const menuBtn = document.querySelector("header .menu-open");
    const closeBtn = document.querySelector(".gnb .close");
    const gnb = document.querySelector(".gnb");

    menuBtn.addEventListener("click", () => {
      gnb.classList.add("on");
    });

    closeBtn.addEventListener("click", () => {
      gnb.classList.remove("on");
    });


    // 이미지 슬라이드
    const next = document.querySelectorAll(".next");
    const prev = document.querySelectorAll(".prev");
    const slider = document.querySelectorAll(".slider");

    for (let i = 0; i <= 1; i++) {
      next[i].addEventListener("click", (e) => {
        slider[i].scrollTo({
          left: slider[i].scrollLeft + 1493,
          behavior: 'smooth'
        });
      });
      
      prev[i].addEventListener("click", (e) => {
        slider[i].scrollTo({
          left: slider[i].scrollLeft - 1493,
          behavior: 'smooth'
        });
      });
    }

    // 이미지 효과
    const imgList = document.querySelectorAll(".img_list");
    for (const img of imgList) {
      img.addEventListener("mouseover", (e) => {
        if (!e.target.matches(".img_list")) return;
        console.log(e.target, " 마우스오버 ");
        e.target.classList.add("mouse-over");
      });
      img.addEventListener("mouseout", (e) => {
        if (!e.target.matches(".img_list")) return;
        console.log(e.target, " 마우스아웃 ");
        e.target.classList.remove("mouse-over");
      });
    }

    // 모달 띄우기
    const [...$media] = document.querySelectorAll(".img_list");
    console.log($media);
    const $modal = document.querySelector(".modal-wrap");
    const $closeBtn = document.querySelector(".modal-close");
    const $backdrop = document.getElementById("backdrop");
    console.log($closeBtn);

    $media.forEach(($card) => {
      $card.addEventListener("click", (e) => {
        $modal.classList.remove("none");
        $backdrop.classList.remove("none");
      });
    });

    $closeBtn.addEventListener("click", (e) => {
      $modal.classList.add("none");
      $backdrop.classList.add("none");
    });

    $backdrop.addEventListener("click", (e) => {
      if (
        !e.target.closest(".modal-wrap") &&
        !e.target.closest(".media-card")
      ) {
        $modal.classList.add("none");
        $backdrop.classList.add("none");
      }
    });

    document.addEventListener("DOMContentLoaded", function () {
      var ratings = document.querySelectorAll(".rating");

      ratings.forEach(function (ratingContainer) {
        var rating = parseFloat(ratingContainer.getAttribute("data-rating"));
        var starsHtml = "";

        // Calculate filled stars
        var filledStars = Math.floor(rating / 2);
        var halfStar = rating % 2 !== 0;

        // Create filled stars
        for (var i = 0; i < filledStars; i++) {
          starsHtml += '<i class="fa-solid fa-star filled"></i>';
        }

        // Add half star if necessary
        if (halfStar) {
          starsHtml += '<i class="fa-solid fa-star-half-alt filled"></i>';
        }

        // Create empty stars to complete 5 stars
        for (var i = filledStars + (halfStar ? 1 : 0); i < 5; i++) {
          starsHtml += '<i class="fa-solid fa-star"></i>';
        }

        // Insert stars into the rating container
        ratingContainer.innerHTML = starsHtml;
      });
    });
  </script>
</html>
