function debounce(callback, wait) {
    let timeout;
    return (...args) => {
      clearTimeout(timeout);
      timeout = setTimeout(() => callback(...args), wait);
    };
  }

// 회원가입 입력 검증 처리

// 계정 입력 검증
const $nicknameInput = document.querySelector("#nickname input");
let nicknameFlag = false;
let emailFlag = false;
const $pwInput = document.querySelector("#password input");
const $pwCheckInput = document.querySelector("#password_check input");
let pwFlag = false;
const $emailInput = document.querySelector("#email input");
let nicknamePass = false;
let pwPass = false;
let pwChkPass = false;
let emailPass = false;

const $form = document.querySelector("#signUpForm");
checkPass();

function checkPass() {
    const $submit = document.querySelector("#signup-btn");
    const flag = !(nicknamePass && pwPass && pwChkPass && emailPass);
    // $submit.disabled = flag;
    if (flag) {
        // console.log("닫힘");
        $submit.style.background = "gray";
        $submit.disabled = true;
    } else {
        // console.log("열림");
        $submit.style.background = "#4CAF50";
        $submit.disabled = false;
    }
}
// 닉네임 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(nicknameValue) {
    const res = await fetch(
        `http://localhost:8989/user/check?type=nickname&keyword=${nicknameValue}`
    );
    const flag = await res.json();

    nicknameFlag = flag;
}

$nicknameInput.addEventListener(
    "keyup",
    debounce(async (e) => {
        // 아이디 검사 정규표현식
        

        // 입력값 읽어오기
        const nicknameValue = $nicknameInput.value;

        // 검증 메시지를 입력할 span
        const $nicknameChk = document.querySelector("#nicknameChk");

        if (nicknameValue.trim() === "") {
            $nicknameInput.style.borderColor = "red";
            $nicknameChk.innerHTML =
                '<b class="warning"><i class="far fa-times-circle"></i>[닉네임은 필수값입니다.]</b>';
            nicknamePass = false;
        } else if (nicknameValue.trim().length >8 || nicknameValue.trim().length < 2) {
            $nicknameInput.style.borderColor = "red";
            $nicknameChk.innerHTML =
                '<b class="warning"><i class="far fa-times-circle"></i>[닉네임은 2~8글자 사이로 입력하세요]</b>';
            nicknamePass = false;
        } else {
            // 닉네임 중복검사
            await fetchDuplicateCheck(nicknameValue);

            if (nicknameFlag) {
                $nicknameInput.style.borderColor = "red";
                $nicknameChk.innerHTML =
                    '<b class="warning"><i class="far fa-times-circle"></i>[아이디가 중복되었습니다. 다른 아이디를 사용하세요!]</b>';
                nicknamePass = false;
            } else {
                $nicknameInput.style.borderColor = "skyblue";
                $nicknameChk.innerHTML =
                    '<b class="success"><i class="far fa-check-circle"></i>[사용가능한 아이디입니다.]</b>';
                nicknamePass = true;
            }
        }
        checkPass();
    }, 500)
);

function checkPassword() {
    const $pwChk2 = document.querySelector("#pwChk2");
    const pwValue = $pwInput.value;
    const pwValue2 = $pwCheckInput.value;
    if (pwValue2.length === 0 || !pwFlag) {
        $pwCheckInput.style.borderColor = "gray";
        $pwChk2.innerHTML = "<i class='fas fa-pen'></i>";
        pwChkPass = false;
    } else {
        if (pwValue2 !== pwValue) {
            $pwCheckInput.style.borderColor = "red";
            $pwChk2.innerHTML =
                '<b class="warning"><i class="far fa-times-circle"></i>[비밀번호가 일치하지 않습니다.]</b>';
            pwChkPass = false;
        } else {
            $pwCheckInput.style.borderColor = "green";
            $pwChk2.innerHTML =
                '<b class="success"><i class="far fa-check-circle"></i>[비밀번호가 일치합니다.]</b>';
            pwChkPass = true;
        }
    }
    checkPass();
}
// 패스워드 검사
$pwInput.addEventListener(
    "keyup",
    debounce(() => {
        const passwordPattern =
            /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*?_~])[A-Za-z\d!@#$%^&*?_~]{8,}$/;
        const $pwChk = document.querySelector("#pwChk");
        const pwValue = $pwInput.value;
        if (pwValue.length >= 8) {
            if (pwValue.trim() === "") {
                $pwInput.style.borderColor = "red";
                $pwChk.innerHTML =
                    '<b class="warning"><i class="far fa-times-circle"></i>[비밀번호는 필수값입니다.]</b>';
                pwFlag = false;
                pwPass = false;
            } else if (!passwordPattern.test(pwValue)) {
                $pwInput.style.borderColor = "red";
                $pwChk.innerHTML =
                    '<b class="warning"><i class="far fa-times-circle"></i>[비밀번호는 영문과 특수문자를 포함해야 합니다.]</b>';
                pwFlag = false;
                pwPass = false;
            } else {
                $pwInput.style.borderColor = "green";
                $pwChk.innerHTML =
                    '<b class="success"><i class="far fa-check-circle"></i>[사용할 수 있는 비밀번호입니다.]</b>';
                pwFlag = true;
                pwPass = true;
            }
        } else {
            $pwInput.style.borderColor = "gray";
            $pwChk.innerHTML = "<i class='fas fa-pen'></i>";
            pwFlag = false;
            pwPass = false;
        }
        checkPassword();
        checkPass();
    }, 500)
);
$pwCheckInput.addEventListener("keyup", debounce(checkPassword, 500));

async function fetchDuplicateCheckEmail(emailValue) {
    const res = await fetch(
        `http://localhost:8989/user/check?type=email&keyword=${emailValue}`
    );
    const flag = await res.json();

    emailFlag = flag;
}

$emailInput.addEventListener(
    "keyup",
    debounce(async (e) => {
        const emailPattern =
            /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        const emailValue = $emailInput.value;
        const $emailChk = document.querySelector("#emailChk");

        if (emailValue.trim() === "") {
            $emailInput.style.borderColor = "red";
            $emailChk.innerHTML =
                '<b class="warning"><i class="far fa-times-circle"></i>[이메일은 필수값입니다.]</b>';
            emailPass = false;
        } else if (!emailPattern.test(emailValue)) {
            $emailInput.style.borderColor = "red";
            $emailChk.innerHTML =
                '<b class="warning"><i class="far fa-times-circle"></i>[이메일형식에 맞게 입력해주세요. ex)abc1234@blabla.com]</b>';
            emailPass = false;
        } else {
            await fetchDuplicateCheckEmail(emailValue);
            if (emailFlag) {
                $emailInput.style.borderColor = "red";
                $emailChk.innerHTML =
                    '<b class="warning"><i class="far fa-times-circle"></i>[이미 사용된 이메일입니다. 다른 이메일을 입력해주세요.]</b>';
                emailPass = false;
            } else {
                $emailInput.style.borderColor = "skyblue";
                $emailChk.innerHTML =
                    '<b class="success"><i class="far fa-check-circle"></i>[사용가능한 이메일입니다.]</b>';
                emailPass = true;
            }
        }
        checkPass();
    }, 500)
);
