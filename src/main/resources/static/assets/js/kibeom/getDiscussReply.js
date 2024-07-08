// callApi 함수 정의

const callApi = async (url, method = 'GET', payload = null) => {
    const requestInfo = {
        method,
    };

    if (payload) {
        requestInfo.headers = { 'content-type': 'application/json' };
        requestInfo.body = JSON.stringify(payload);
    }

    const res = await fetch(url, requestInfo);

    if (res.status === 403) {
        alert('접근 권한이 없습니다.');
        return null;
    }

    if (method === 'DELETE' && res.status === 204) {
        return true; // 삭제 성공 시 true 반환
    }

    return await res.json();
};

document.addEventListener('DOMContentLoaded', function () {
    const discussionNo = document.querySelector('.discussion-body').dataset.discNo;
    const commentsContainer = document.getElementById('comments');
    const submitCommentButton = document.getElementById('submitComment');
    const commentForm = document.getElementById('commentForm');

    async function fetchComments() {
        try {
            const response = await fetch(`/api/v1/discuss/reply/${discussionNo}`);
            if (!response.ok) throw new Error('댓글을 불러오는데 실패했습니다.');
            const { dtoList, loginUserDto } = await response.json();


            renderComments(dtoList, loginUserDto);
        } catch (error) {
            console.error(error);
        }
    }

    async function removeComment(rno) {
        const result = await callApi(`http://localhost:8989/api/v1/discuss/reply/` + rno, 'DELETE');
        if (result) {
            const commentElement = document.querySelector(`[data-replyNo="${rno}"]`).closest('.comment-card');
            if (commentElement) {
                commentElement.remove();
            }
        }
        await fetchComments();
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
                await fetchComments(); // 전체 댓글 다시 불러오기
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
        if (comments.length === 0) {
            const commentElement = document.createElement('div');
            commentElement.classList.add('comment-card');
            let tag = `<div>댓글이 없습니다.</div>`;
            commentElement.innerHTML = tag;
            commentsContainer.appendChild(commentElement);
        } else {
            const replyCount = document.createElement('h2');
            replyCount.textContent = `댓글 [${comments.length}]`
            document.getElementById('comments').appendChild(replyCount)

            comments.forEach(comment => {
                addCommentToDOM(comment, loginUserDto);
            });
        }
    }

    function addCommentToDOM(comment, loginUserDto) {
        const oldDate = new Date(comment.discussionReplyCreatedAt);
        const newDate = new Date(comment.discussionReplyUpdatedAt);

        const commentElement = document.createElement('div');
        commentElement.classList.add('comment-card');
        let tag;


        if (loginUserDto && loginUserDto.nickname === comment.nickname) {
            // 내가 쓴 댓글
            tag = `
            <div class="comment-header self comment-header-self" data-replyNo="${comment.discussionReplyNo}">
                <span class="comment-nickname nickname" id="my-nickname" data-email="${loginUserDto.email}">${comment.nickname || comment.email}</span> `;

            if (oldDate.getTime() + 1000 < newDate.getTime()) { // 내가 쓴 글이 수정됐을 때
                tag += `
                        <br>
                        <span class="comment-date" id="my-date">*수정됨*&nbsp;&nbsp;&nbsp; ${oldDate.toLocaleString('ko-KR', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                })}</span>
                    </div>
            <div id="modify-delete-btn" class="modifyAndDeleteBtn">
                    <button class="deleteBtn" data-rno="${comment.discussionReplyNo}">삭제</button>
                    <button class="modifyBtn" data-rno="${comment.discussionReplyNo}" data-email="${comment.email}">수정</button>
            </div>
            <div id="cancle-clear-btn" class="cancleAndClearBtn">
                    <button class="cancleBtn" data-rno="${comment.discussionReplyNo}">취소</button>
                    <button class="clearBtn" data-rno="${comment.discussionReplyNo}" data-email="${comment.email}">완</button>
            </div>`;
            } else { // 내가 쓴 글이 수정되지 않았을 때
                tag += `
                        <br>
                        <span class="comment-date" id="my-date">${oldDate.toLocaleString('ko-KR', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                })}</span>
                    </div>
            <div id="modify-delete-btn" class="modifyAndDeleteBtn">
                    <button class="deleteBtn" data-rno="${comment.discussionReplyNo}">삭제</button>
                    <button class="modifyBtn" data-rno="${comment.discussionReplyNo}" data-email="${comment.email}">수정</button>
            </div>
            <div id="cancle-clear-btn" class="cancleAndClearBtn">
                    <button class="cancleBtn" data-rno="${comment.discussionReplyNo}">취소</button>
                    <button class="clearBtn" data-rno="${comment.discussionReplyNo}" data-email="${comment.email}">완료</button>
            </div>
`;
            }

            tag += `
            <div class="comment-body self" id="my-content">
                <p class="reply-comment">${comment.discussionReplyContent}</p>
            </div>
            `;
        } else {
            // 남이 쓴 댓글
            tag = `
            <div class="comment-header" data-replyNo="${comment.discussionReplyNo}">
                <span class="comment-nickname nickname" data-email="${comment.email}">${comment.nickname || comment.email}</span> `;

            if (oldDate.getTime() + 1000 < newDate.getTime()) { // 남이 쓴 글이 수정됐을 때
                tag += `
                        <br>
                        <span class="comment-date">${oldDate.toLocaleString('ko-KR', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                })}&nbsp;&nbsp;&nbsp;*수정됨*</span>
                    </div>`;
            } else { // 남이 쓴 글이 수정되지 않았을 때
                tag += `
                        <br>
                        <span class="comment-date">${oldDate.toLocaleString('ko-KR', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                })}</span>
                    </div>`;
            }

            tag += `
            <div class="comment-body">
                <p class="reply-comment">${comment.discussionReplyContent}</p>
            </div>`;
        }


        commentElement.innerHTML = tag;

        commentsContainer.appendChild(commentElement);
    }

    fetchComments();
    submitCommentButton.addEventListener('click', submitComment);

    // 댓글 삭제 기능
    commentsContainer.addEventListener('click', e => {
        if (e.target.matches('.deleteBtn')) {
            const $parentNode = e.target.closest('.comment-card');
            const rno = $parentNode.querySelector('.deleteBtn').dataset.rno;
            removeComment(rno)
        }
    });



    // 댓글 수정 기능 추가
    commentsContainer.addEventListener('click', e => {
        if (e.target.matches('.modifyBtn')) {
            const $parentNode = e.target.closest('.comment-card');
            const $modifyBtn = e.target;
            const $delBtn = $parentNode.querySelector('.deleteBtn');
            const $cancelBtn = $parentNode.querySelector('.cancleBtn')
            const $clearBtn = $parentNode.querySelector('.clearBtn')
            const $commentBody = $parentNode.querySelector('.comment-body');
            const $oldP = $commentBody.querySelector('.reply-comment');
            const cancelAndClear = $parentNode.querySelector('.cancleAndClearBtn')
            const modifyAndDel = $parentNode.querySelector('.modifyAndDeleteBtn')
            const originalText = $oldP.textContent;

            const $newInput = document.createElement('textarea');
            $newInput.value = originalText;
            $newInput.classList.add('modify-input');

            console.log(cancelAndClear)
            console.log(modifyAndDel)
            modifyAndDel.style.display = "none";
            cancelAndClear.style.display = "block";

            $commentBody.innerHTML = '';
            $commentBody.appendChild($newInput);

            // $modifyBtn.textContent = '완료';
            // $delBtn.textContent = '취소';



            // $modifyBtn.classList.add('saveBtn');
            // $delBtn.classList.add('cancelBtn');
            // $modifyBtn.classList.remove('modifyBtn');
            // $delBtn.classList.remove('deleteBtn');

            // 완료 버튼 클릭 이벤트
            const saveHandler = (e) => {

                const updatedContent = $newInput.value;

                const replyNo = $modifyBtn.getAttribute('data-rno');
                const email = $modifyBtn.getAttribute('data-email');

                // 폼 생성
                const form = document.createElement('form');
                form.method = 'post';
                form.action = '/discussion/reply/modify';

                // 폼 데이터 추가
                const inputReplyNo = document.createElement('input');
                inputReplyNo.type = 'hidden';
                inputReplyNo.name = 'discussionReplyNo';
                inputReplyNo.value = replyNo;

                const inputEmail = document.createElement('input');
                inputEmail.type = 'hidden';
                inputEmail.name = 'email';
                inputEmail.value = email;

                const inputContent = document.createElement('input');
                inputContent.type = 'hidden';
                inputContent.name = 'discussionReplyContent';
                inputContent.value = updatedContent;

                const inputDiscussionNo = document.createElement('input');
                inputDiscussionNo.type = 'hidden';
                inputDiscussionNo.name = 'discussionNo';
                inputDiscussionNo.value = discussionNo;

                form.appendChild(inputReplyNo);
                form.appendChild(inputEmail);
                form.appendChild(inputContent);
                form.appendChild(inputDiscussionNo);

                if (originalText === updatedContent) {
                    e.preventDefault();
                    $modifyBtn.setAttribute('disabled', 'disabled');
                } else {
                    document.body.appendChild(form);
                    form.submit();
                }
            };

            // 취소 버튼 클릭 이벤트
            const cancelHandler = () => {
                $delBtn.setAttribute("onclick", '')
                $commentBody.innerHTML = `<p class="reply-comment">${originalText}</p>`;

                cancelAndClear.style.display = "none";
                modifyAndDel.style.display = "block";

                // $modifyBtn.classList.add('modifyBtn');
                // $delBtn.classList.add('deleteBtn');
                // $modifyBtn.classList.remove('saveBtn');
                // $delBtn.classList.remove('cancelBtn');

                // 기존 이벤트 리스너를 제거하여 메모리 누수를 방지
                $modifyBtn.removeEventListener('click', saveHandler);
                $delBtn.removeEventListener('click', cancelHandler);
            };

            // 기존 이벤트 리스너를 제거하고 새로 추가
            $modifyBtn.removeEventListener('click', saveHandler);
            // $cancelBtn.removeEventListener('click', cancelHandler);
            // $clearBtn.addEventListener('click', cancelHandler);
            $clearBtn.addEventListener('click', saveHandler);
            $cancelBtn.addEventListener('click', cancelHandler);
        }

    });

});

// 삭제 버튼 확인
