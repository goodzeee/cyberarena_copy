const $finishBtn = document.getElementById('finish');
const $title = document.getElementById('title');
const $offer = document.getElementById('detail');
const $media = document.getElementById('media');
const $modalWrap = document.querySelector('.modal-wrap');
const $replySubmitBtn = document.getElementById('submitComment');
const $replyTextArea = document.getElementById('reply-textarea');

let isExist = false; // 댓글 등록창에 값이 있냐 없냐

const validate = [$title, $offer];
let isValidate = [false, false];
//
// for (let i = 0; i < validate.length; i++) {
//     validate[i].addEventListener('keyup', e => {
//         if (validate[i].value !== '' ) {
//             isValidate[i] = true;
//         } else {
//             isValidate[i] = false;
//         }
//         // 미디어값 비어도 완료 들어감, 예외처리 해야함
//
//         if (isValidate[0] === true && isValidate[1] === true) {
//             $finishBtn.style.cursor = 'pointer';
//             $finishBtn.style.backgroundColor = "#333";
//             $finishBtn.setAttribute('type', 'submit');
//         } else {
//             $finishBtn.style.cursor = 'default';
//             $finishBtn.style.width = '100px'
//             $finishBtn.style.backgroundColor = "#ccc";
//             $finishBtn.removeAttribute('type');
//         }
//     });
// }


for(let i = 0; i < 1; i++) {
    $replyTextArea.addEventListener('keyup', e => {
        if ($replyTextArea.value.trim() > 0) {
            isExist = true;
        } else {
            isExist = false;
        }

        if (isExist) {
            $replySubmitBtn.style.backgroundColor = "#28a745"
        } else {
            $replySubmitBtn.style.pointerEvents = 'none'
            $replySubmitBtn.style.backgroundColor = "lightgray";
        }

    })
}





const $modify = document.getElementById('modify');

console.log($modify)
const $backdrop = document.getElementById('backdrop')


$modify.addEventListener('click', e =>  {

        console.log("modal ", $modalWrap)

        $modalWrap.style.display = 'flex'
        console.log('removed modal, ', $modalWrap)
        $backdrop.classList.remove('none');

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


// 0626 에러나서 일단 주석처리
// $modalInput.addEventListener('keydown', e => {
//     if ($modalInput.value !== '') {
//         $modalInput.style.backgroundColor = "#E7F0FE";
//     } else {
//         $modalInput.style.backgroundColor = "white";
//     }
// })



