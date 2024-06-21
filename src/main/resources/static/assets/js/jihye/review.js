import {fetchReviews} from "./getReview.js";
import { fetchReviewPost } from "./postReview.js";

// 전역 변수
export const BASE_URL = 'http://localhost:8989/review';
const mno = document.getElementById('wrap').dataset.mno; // 미디어 글번호
console.log('미디어 글번호: ', mno);


// 함수 정의
// 데이터를 받아온 후 실행되는 함수
function renderReplies(reviews) {
  let tag = '';
  if (reviews && reviews.length > 0) {
      reviews.forEach(({ rno, email, text, reviewCreatedAt, userRating, discussionStatus }) => {
          tag += `
              <div id='replyContent' class='card-body' data-reply-id='${rno}'>
                  <div class='row user-block'>
                      <span class='col-md-3'>
                          <b>${email}</b>
                      </span>
                      <span class='offset-md-6 col-md-3 text-right'><b>${reviewCreatedAt}</b></span>
                  </div><br>
                  <div class='row'>
                      <div class='col-md-9'>${text}</div>
                      <div class='col-md-10'>${userRating}</div>
                      <div class='col-md-11'>${discussionStatus}</div>
                      <div class='col-md-3 text-right'>
                          <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                          <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
                      </div>
                  </div>
              </div>
          `;
      });
  } else {
      tag = `<div id='reviewContent' class='card-body'>등록된 리뷰가 아직 없습니다 😣</div>`;
  }
  document.getElementById('replyData').innerHTML = tag;
}


async function fetchReviews() {
  const res = await fetch (`${BASE_URL}/${mno}`);
  const reviews = await res.json();
  
  // 리뷰 목록 렌더링
  renderReplies(reviews);
}

// 실행 코드

// 리뷰 목록 서버에서 불러오기 
// fetchReviews();

// // 리뷰 작성 이벤트 등록
// document.getElementById('reviewBtn').addEventListener('click', e => {

//   // 리뷰 등록 로직 - postReview에서 처리
//   fetchReviewPost();
// })