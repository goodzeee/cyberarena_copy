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
            console.log(dtoList)
            console.log(loginUserDto)
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

                addCommentToDOM(newComment);
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
                <button>삭제</button>
            </span>
            <span class="modify-and-delete"  
            onclick="window.location.href='/discussion/reply/modify?rno=' + ${comment.discussionReplyNo}">
                <button>수정</button>
            </span>`
        }
        commentElement.innerHTML += `<div class="comment-body">
            <p>${comment.discussionReplyContent}</p>
        </div>
    `;
        commentsContainer.appendChild(commentElement);
    }

    fetchComments();
    submitCommentButton.addEventListener('click', submitComment);
});


