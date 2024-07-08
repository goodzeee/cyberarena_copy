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
              <section class="link" data-reply-count="${discussions.replyCount}" onclick="location.href='/discussion/detail?dno=${discussions.discussionNo}'">
                <h2>${discussions.discussionTitle}</h2>
                <div class="poster_info">
                  <span class="media-title" data-media-no="${discussions.mediaNo}">${discussions.mediaTitle}</span>
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
                    <h2 class="card-title media-title" data-media-no="${review.mediaNo}">${review.mediaTitle}</h2>
                    <div class="time-view-wrapper">
                      <div class="nickname" data-email="${review.email}">${review.nickname}</div>
                      <div
                        class="rating"
                        data-rating="${review.userRating}"
                      ></div>
                    </div>
                    <div class="card-content"><img src="${review.imageUrl}" alt="Media Image">  
                      <span>${review.reviewText}</span>
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
          <c:forEach items="${media}" var="media">
            <div class="media_list" data-media-no="${media.mediaNo}">
              <img
              class="img_list"
              src="${media.imageUrl}"
              alt="Media Image">
              ${media.mediaTitle}
            </div>
          </c:forEach>
          
        </div>
        <div class="next"><i class="fa-solid fa-angle-right"></i></div>
      </div>
    </main>

    <!--모달 -->
    <div class="modal-wrap none">
      <div class="img-wrap">
       <img src="" alt="">
      </div>
      <div class="media-info">
        <h2></h2>
        <div class="detail-info">
          <span class="rating"></span>
          <span class="creator"></span>
          <span class="created-at"></span>
        </div>
        <div class="description"></div>
      </div>
      <div class="modal-close">닫기</div>
      <div class="reviews">
        <div class="review-title">
          <h2 class="nickname"></h2>
          <span></span>
          <div class="review-list">
            <span class="review-content"></span>
          </div>
        </div>
      </div>
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
    const [...$media] = document.querySelectorAll(".media_list");
    const $modal = document.querySelector(".modal-wrap");
    const $closeBtn = document.querySelector(".modal-close");
    const $backdrop = document.getElementById("backdrop");

    $media.forEach(($card) => {
      $card.addEventListener("click", async (e) => {
        $modal.classList.remove("none");
        $backdrop.classList.remove("none");
        const mediaNo = $card.dataset.mediaNo;
        const res = await fetch(`/media/info?mediaNo=\${mediaNo}`);
        const json = await res.json();
        $modal.querySelector(".img-wrap img").setAttribute("src", json.imageUrl);
        $modal.querySelector(".media-info h2").textContent = json.mediaTitle;
        $modal.querySelector(".rating").textContent = "평균 별점: " +json.rating;
        $modal.querySelector(".creator").textContent = "제작진: " +json.creator;
        $modal.querySelector(".created-at").textContent = json.createdAt;
        $modal.querySelector(".description").textContent = json.mediaDesc;
        const res2 = await fetch(`/media/review-info?mediaNo=\${mediaNo}`);
        const json2 = await res2.json();
        const reviewList = json2.reviews;
        const $reviews = $modal.querySelector('.reviews');
        $reviews.innerHTML = `<h2>리뷰</h2>
                               <a href="/review/list/\${mediaNo}">더보기<i class="fa-solid fa-angle-right"></i></a>`;
        const latestReviews = reviewList.slice(0, 8);
        latestReviews.forEach(({ nickname, text, userRating, email }) => {

          $reviews.innerHTML += `
          <div class="review-title">
             <h2 class=nickname data-email="\${email}">\${nickname}</h2>
            <span><i class="fa-solid fa-star filled"></i>\${userRating}</span>
            <div class="review-list">
              <span class="review-content">\${text}</span>
            </div>
          </div>
          `;
        });
        
      
      });
    });

    document.addEventListener("click", e => {
      if(e.target.matches(".nickname")) {
        const email = e.target.dataset.email;
        window.location.href = `/user/user-info/\${email}`;
        return;
      }
      if(e.target.matches(".media-title")) {
        const mediaNo = e.target.dataset.mediaNo;
        window.location.href = `/review/list/\${mediaNo}`;
        return;
      }
    })

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

    // 별점 효과
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
