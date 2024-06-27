
const menuBtn = document.querySelector("header .menu-open");
const closeBtn = document.querySelector(".gnb .close");
const gnb = document.querySelector(".gnb");

menuBtn.addEventListener("click", () => {
    gnb.classList.add("on");
});

closeBtn.addEventListener("click", () => {
    gnb.classList.remove("on");
});

// 모달 띄우기

const [...$media] = document.querySelectorAll('.media-card')
// console.log($media);
const $modal = document.querySelector('.modal-wrap')
const $closeBtn = document.querySelector('.modal-close')
const $backdrop = document.getElementById('backdrop')
// console.log($closeBtn)

$media.forEach($card => {
    $card.addEventListener('click', e => {
        $modal.classList.remove('none');
        $backdrop.classList.remove('none')
    });
});

$closeBtn.addEventListener('click', e => {
    $modal.classList.add('none')
    $backdrop.classList.add('none')
})


// document.addEventListener('click', e => {
//
//     if (!e.target.closest('.modal-wrap') && !e.target.closest('.media-card')) {
//         $modal.classList.add('none');
//         $backdrop.classList.add('none')
//     }
// });


