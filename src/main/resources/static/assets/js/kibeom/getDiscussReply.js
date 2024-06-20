document.addEventListener('DOMContentLoaded', function() {
    const discussionNo = document.querySelector('input[name="discussionNo"]').value;
    const commentsContainer = document.getElementById('comments');
    const submitCommentButton = document.getElementById('submitComment');
    const commentForm = document.getElementById('commentForm');

    async function fetchComments() {
        try {
            const response = await fetch(`/api/v1/discuss/reply/${discussionNo}`);
            if (!response.ok) throw new Error('댓글을 불러오는데 실패했습니다.');
            const comments = await response.json();
            renderComments(comments);
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

    function renderComments(comments) {
        commentsContainer.innerHTML = '';
        comments.forEach(comment => {
            addCommentToDOM(comment);
        });
    }

    function addCommentToDOM(comment) {
        const commentElement = document.createElement('div');
        console.log(comment)
        commentElement.classList.add('comment-card');
        commentElement.innerHTML = `
            <div class="comment-header" data-replyNo="${comment.discussionReplyNo}">
                <span class="comment-nickname">${comment.email}</span>
                <span class="comment-date">${new Date(comment.discussionReplyCreatedAt).toLocaleString()}</span>
            </div>
            
                <span class="modify-and-delete"><button>삭제</button></span>
                <span class="modify-and-delete"><button>수정</button></span>
            
            <div class="comment-body">
                <p>${comment.discussionReplyContent}</p>
            </div>
        `;
        commentsContainer.appendChild(commentElement);
    }

    fetchComments();
    submitCommentButton.addEventListener('click', submitComment);
});