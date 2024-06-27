const $finishBtn = document.getElementById('finish');
const $title = document.getElementById('title');
const $offer = document.getElementById('detail');
const $media = document.getElementById('media');
const $modalWrap = document.querySelector('.modal-wrap');

const validate = [$title, $offer];
let isValidate = [false, false];

for (let i = 0; i < validate.length; i++) {
    validate[i].addEventListener('keyup', e => {
        if (validate[i].value !== '' ) {
            isValidate[i] = true;
        } else {
            isValidate[i] = false;
        }
        // 미디어값 비어도 완료 들어감, 예외처리 해야함

        if (isValidate[0] === true && isValidate[1] === true) {
            $finishBtn.style.cursor = 'pointer';
            $finishBtn.style.backgroundColor = "#333";
            $finishBtn.setAttribute('type', 'submit');
        } else {
            $finishBtn.style.cursor = 'default';
            $finishBtn.style.width = '100px'
            $finishBtn.style.backgroundColor = "#ccc";
            $finishBtn.removeAttribute('type');
        }
    });
}

const $makeBtn = document.querySelector('.make-discussion')
const $backdrop = document.getElementById('backdrop')

const $detail = document.querySelector('.list-wrap')
document.addEventListener('click', e => {
    if (!e.target.closest('.card')) return;
    const dno = e.target.closest('.card').dataset.dno;
    window.location.href = '/discussion/detail?dno=' + dno;
})

const menuBtn = document.querySelector("header .menu-open");
const closeBtn = document.querySelector(".gnb .close");
const gnb = document.querySelector(".gnb");

menuBtn.addEventListener("click", () => {
    gnb.classList.add("on");
});

closeBtn.addEventListener("click", () => {
    gnb.classList.remove("on");
});

// document.addEventListener('DOMContentLoaded', function() {
    const makeDiscussionButton = document.querySelector('.make-discussion');

    makeDiscussionButton.addEventListener('click', e =>  {
        if (!isLoggedIn) {
            alert('로그인이 필요한 서비스입니다.');
            e.preventDefault();
        } else {
            console.log("modal ", $modalWrap)
            // $modalWrap.classList.remove('none');
            $modalWrap.style.display = 'block'
            console.log('removed modal, ', $modalWrap)
            $backdrop.classList.remove('none');
        }
    });
// });


// 입력값 상호작용

const $modalInput = document.querySelector('.modal-input');

$title.addEventListener('keyup', e => {
    if ($title.value !== '') {
        $title.style.backgroundColor = "#E7F0FE";
    } else {
        $title.style.backgroundColor = "white";
    }
});

$offer.addEventListener('keyup', e => {
    if ($offer.value !== '') {
        $offer.style.backgroundColor = "#E7F0FE";
    } else {
        $offer.style.backgroundColor = "white";
    }
});

$modalInput.addEventListener('keydown', e => {
    if ($modalInput.value !== '') {
        $modalInput.style.backgroundColor = "#E7F0FE";
    } else {
        $modalInput.style.backgroundColor = "white";
    }
})