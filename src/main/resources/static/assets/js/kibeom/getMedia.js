// callApi 함수 정의
const callApi = async (url, method = 'GET', payload = null) => {
    const requestInfo = {
        method,
    };

    if (payload) {
        requestInfo.headers = {'content-type': 'application/json'};
        requestInfo.body = JSON.stringify(payload);
    }

    const res = await fetch(url, requestInfo);

    if (res.status === 403) {
        alert('접근 권한이 없습니다.');
        return null;
    }

    return await res.json();
};

// fetchMedia 함수 정의
const fetchMedia = async (url) => {
    const mediaResponse = await callApi(url);
    return mediaResponse;
};

// renderReplies 함수 정의
const renderReplies = (mediaResponse) => {
    let tag = '';
    // 데이터 들고왔으니 if문 생략
    // if (mediaResponse && mediaResponse.length > 0) {

    if (!mediaResponse) {

        tag = `<div>검색 결과가 없습니다.</div>`;

    } else {

        tag += `
                <div class="modal-right-wrap">
                    <div class="modal-media-left">
                        <div class="modal-media-wrap">
                            <img src="${mediaResponse.imageUrl}" alt="모달 이미지">
                        </div>
                    </div>
                    <div class="modal-media-right">
                        <div class="modal-media-title">${mediaResponse.mediaTitle}</div>
                        <div class="modal-media-rating">${mediaResponse.rating}</div>
                    </div>
                </div>
            `;

    }
    const fetchWrap = document.querySelector('.fetch-wrap');

    fetchWrap.innerHTML = tag;
};

// 디바운스 함수 정의
const debounce = (func, delay) => {
    let debounceTimer;
    return function (...args) {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => func.apply(this, args), delay);
    };
};

// 이벤트 리스너 추가
const handleInput = async (event) => {
    const searchQuery = event.target.value;
    if (searchQuery.trim() === '') {
        document.querySelector('.fetch-wrap').innerHTML = '<div>검색 결과가 없습니다.</div>';
        return;
    }
    const encodedQuery = encodeURIComponent(searchQuery);

    // URL에 쿼리 파라미터 추가
    const url = `http://localhost:8989/api/v1/media/mediaList/${encodedQuery}`;
                    //   http://localhost:8989/api/v1/media/mediaList/%EC%95%94%EC%82%B4
    // 서버로 fetch 요청 보내기
    const mediaResponse = await fetchMedia(url);

    // 응답 데이터 렌더링
    renderReplies(mediaResponse);
};


const debouncedHandleInput = debounce(handleInput, 300);

document.getElementById('searchMediaInput').addEventListener('input', debouncedHandleInput);