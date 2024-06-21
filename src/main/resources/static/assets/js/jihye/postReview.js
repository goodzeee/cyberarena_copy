import { BASE_URL } from "./review.js";

// 서버에 리뷰 등록을 요청하는 비동기 함수
export const fetchReviewPost = async () => {

  const payload = {
    reviewText: document.getElementById('reviewText').value,
    userRating: document.getElementById('userRating').value,
    discussionStatus: document.getElementById('discussionStatus').value,
    email: document.getElementById('reviewText').value,
    mediaNo: document.getElementById('wrap').dataset.mno
  };

  console.log(payload);

  const res = await fetch(`${BASE_URL}`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify(payload)
  });

  const reviews = await res.json();
  console.log(reviews);
};