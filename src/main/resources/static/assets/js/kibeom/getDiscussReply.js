document.addEventListener('DOMContentLoaded', function() {
    const discussionNo = document.querySelector('input[name="discussionNo"]').value;
    const commentsContainer = document.getElementById('comments');
    const submitCommentButton = document.getElementById('submitComment');
    const commentForm = document.getElementById('commentForm');

    async function fetchComments() {
        try {
            const response = await fetch(`/api/v1/discuss/reply/${discussionNo}`);
            if (!response.ok) throw new Error('댓글을 불러오는데 실패했습니다.');
            const {dtoList, loginUserDto} = await response.json();
            // 0626 comments안에 로그인 유저정보 객체도 담음
            renderComments(dtoList, loginUserDto);
        } catch (error) {
            console.error('Error fetching comments:', error);
        }
    }

    async function submitComment() {
        const formData = new FormData(commentForm);
        const data = {
            discussionNo: formData.get('discussionNo'),
            email: formData.get('email'),
            discussionReplyContent: formData.get('discussionReplyContent')
        };

        try {
            const response = await fetch('/api/v1/discuss/reply', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                const newComment = await response.json();
                fetchComments(); // 전체 댓글 다시 불러오기
                commentForm.reset();
            } else {
                console.error('Error submitting comment:', response.statusText);
            }
        } catch (error) {
            console.error('Error submitting comment:', error);
        }
    }

    function renderComments(comments, loginUserDto) {
        commentsContainer.innerHTML = '';
        comments.forEach(comment => {
            addCommentToDOM(comment, loginUserDto);
        });
    }

    function addCommentToDOM(comment, loginUserDto) {
        const commentElement = document.createElement('div');
        commentElement.classList.add('comment-card');
        commentElement.innerHTML = `
        <div class="comment-header" data-replyNo="${comment.discussionReplyNo}">
            <span class="comment-nickname">${comment.nickname || comment.email}</span> 
            <span class="comment-date">${new Date(comment.discussionReplyCreatedAt).toLocaleString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' })}</span>
        </div>`;

        if (loginUserDto.nickname === comment.nickname) {
            commentElement.innerHTML +=
                `<span class="modify-and-delete"
            onclick="window.location.href='/discussion/reply/remove?rno='+ ${comment.discussionReplyNo} + '&&dno=' + ${comment.discussionNo}">
                <button class="deleteBtn">삭제</button>
            </span>
            <span class="modify-and-delete modify" >
                <button class="modifyBtn" data-rno="${comment.discussionReplyNo}">수정</button>
            </span>`;
        }
        commentElement.innerHTML += `<div class="comment-body">
            <p class="reply-comment">${comment.discussionReplyContent}</p>
            <input class="hide modify-input">
        </div>`;
        commentsContainer.appendChild(commentElement);
    }

    fetchComments();
    submitCommentButton.addEventListener('click', submitComment);
});


const $replyWrap = document.querySelector('.comment-section');

$replyWrap.addEventListener("click", e => {
    if (!e.target.matches('.modifyBtn')) return

    // =========== 변수 정의 영역 ==============
    const $parentWrap = e.target.closest('.comment-card') // 부모
    const $parentDiv = $parentWrap.lastChild // 부모
    const $oldNode = $parentWrap.lastChild.lastElementChild // 교체할 원래 P태그
    const $modifyBtn = $parentWrap.querySelector('.modifyBtn')
    const $deleteBtn = $parentWrap.querySelector('.deleteBtn')
    const $oldText = $parentWrap.querySelector('.reply-comment')
    const $newText = $parentWrap.querySelector('.modify-input')



    $deleteBtn.textContent = "완료"
    $modifyBtn.textContent = "취소"
    // =========== 변수 정의 영역 끝 ==============

    $oldText.classList.add('hide');
    $newText.classList.remove('hide');





    // 수정 취소 이벤트

    // 스코프 옮기면 될듯

    $deleteBtn.addEventListener('click', e => {
        $oldText.classList.remove('hide');
        $newText.classList.add('hide');

    })
    // 수정 다시 보자. 태그 교체 안됨
})
// const 버튼 선언
// if (!$deleteBtn) {}



