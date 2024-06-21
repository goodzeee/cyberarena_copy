import { BASE_URL } from './review.js';
import {renderReplies} from "./getReview.js";

export function renderReplies({ pageInfo, replies }) {
  // 댓글 수 렌더링
  document.getElementById('reviewCnt').textContent = pageInfo.totalCount;

  // 댓글 목록 렌더링
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

  // 페이지 태그 렌더링
  renderPage(pageInfo);
