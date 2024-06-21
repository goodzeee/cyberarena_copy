const $finishBtn = document.getElementById('finish');
const $title = document.getElementById('title');
const $offer = document.getElementById('detail');
const $modal = document.getElementById('modal');

const validate = [$title, $offer];
let isValidate = [false, false];

for (let i = 0; i < validate.length; i++) {
    validate[i].addEventListener('keyup', e => {
        if (validate[i].value !== '') {
            isValidate[i] = true;
        } else {
            isValidate[i] = false;
        }

        if (isValidate[0] === true && isValidate[1] === true) {
            $finishBtn.style.cursor = 'pointer';
            $finishBtn.style.backgroundColor = "#333";
            $finishBtn.setAttribute('type', 'submit');
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

document.addEventListener('DOMContentLoaded', function() {
    const makeDiscussionButton = document.querySelector('.make-discussion');

    makeDiscussionButton.addEventListener('click', function(event) {
        if (!isLoggedIn) {
            alert('로그인이 필요한 서비스입니다.');
            event.preventDefault();
        } else {
            $modal.classList.remove('none');
            $backdrop.classList.remove('none');
        }
    });
});