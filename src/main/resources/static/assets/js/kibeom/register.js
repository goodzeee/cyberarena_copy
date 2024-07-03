const $finishBtn = document.getElementById('finish');
const $title = document.getElementById('title');
const $offer = document.getElementById('detail');
const $media = document.getElementById('searchMediaInput')
const $fetchWrap = document.querySelector('.fetch-wrap');
// const $media = document.getElementById('media');

// const $modalWrap = document.querySelector('.modal-wrap');

$media.addEventListener('keydown', e => {
    $fetchWrap.style.display = 'block';
})


const validate = [$title, $offer, $media];
let isValidate = [false, false, false];


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

$media.addEventListener('keyup', e => {
    if ($media.value !== '') {
        $media.style.backgroundColor = "#E7F0FE";
    } else {
        $media.style.backgroundColor = "white";
    }
})


for (let i = 0; i < validate.length; i++) {
    validate[i].addEventListener('keyup', e => {
        if (validate[i].value !== '' ) {
            isValidate[i] = true;
        } else {
            isValidate[i] = false;
        }
        // 미디어값 비어도 완료 들어감, 예외처리 해야함

        if (isValidate[0] && isValidate[1] && isValidate[2]) {
            $finishBtn.style.cursor = 'pointer';
            $finishBtn.style.backgroundColor = "#333";
            $finishBtn.setAttribute('type', 'submit');
        } else {
            $finishBtn.style.cursor = 'default';
            $finishBtn.style.width = '100px'
            $finishBtn.style.backgroundColor = "#ccc";
            $finishBtn.setAttribute('type', 'button');
        }
    });
}



// 입력값 상호작용

// const $modalInput = document.querySelector('.modal-input');
// console.log($modalInput)
//
// $modalInput.addEventListener('keydown', e => {
//
//     if ($modalInput.value !== '') {
//         $modalInput.style.backgroundColor = "#E7F0FE";
//     } else {
//         $modalInput.style.backgroundColor = "white";
//     }
// })

