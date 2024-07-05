document.addEventListener("DOMContentLoaded", function() {
    const sortSelect = document.getElementById('sortSelect');

    sortSelect.addEventListener('change', function() {
        const sortValue = sortSelect.value;
        fetchSortedDiscussions(sortValue);
    });

    function fetchSortedDiscussions(sort) {
        fetch(`/discussion/list/${sort}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                updateDiscussionList(data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function updateDiscussionList(data) {
        const discussWrap = document.getElementById('discuss-wrap');
        discussWrap.innerHTML = ''; // 기존 내용을 지우기

        if (data.length === 0) {
            discussWrap.innerHTML = '<div class="empty">게시물이 존재하지 않습니다.</div>';
            return;
        }

        data.forEach(d => {
            const card = document.createElement('div');
            card.className = 'card';
            card.setAttribute('data-dno', d.discussionNo);

            card.innerHTML = `
                <h2 class="discussion-title">${d.discussionTitle}</h2>
                <div class="main-content">
                    <span class="media-title"><h3>${d.mediaTitle}</h3></span>
                    <span class="nickname">작성자: ${d.nickname}</span>
                </div>
                <p class="discuss-view-count"><i class="fas fa-eye"></i> ${d.viewCount}</p>
                <p class="reply-count">참여 [${d.replyCount}]</p>
                <p class="discussion-created-at">${d.formattedDiscussionCreatedAt}</p>
            `;

            discussWrap.appendChild(card);
        });
    }
});
