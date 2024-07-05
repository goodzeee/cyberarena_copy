const $finishBtn = document.getElementById('finish');
const $title = document.getElementById('title');
const $offer = document.getElementById('detail');
const $media = document.getElementById('media');
const $listWrap = document.getElementById('whole-wrap');
// const $modalWrap = document.querySelector('.modal-wrap');

const validate = [$title, $offer];
let isValidate = [false, false];

const $detail = document.getElementById('whole-wrap')

$detail.addEventListener('click', e => {

    if (!e.target.closest('.card')) return;

    const dno = e.target.closest('.card').dataset.dno;
    window.location.href = '/discussion/detail?dno=' + dno;
})


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



$listWrap.addEventListener('click', e => {
    // if (!e.target.matches('.card')) return
    console.log("click")
    const dno = e.target.closest('.card').dataset.dno;
    window.location.href = "/discussion/detail?dno=" + dno;
})






const $backdrop = document.getElementById('backdrop')



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

// });


// 입력값 상호작용



