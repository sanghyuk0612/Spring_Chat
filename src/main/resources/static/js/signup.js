// $(document).ready(function() {
//     // 로그인 버튼 클릭 시 이벤트 핸들러
//     $('#login_button').click(function(event) {
//         // 기본 폼 제출 방지
//         event.preventDefault();
//
//         // 아이디와 비밀번호 유효성 검사
//         const username = $('#username').val();
//         const password = $('#password').val();
//
//         if (username === '' || password === '') {
//             alert('아이디와 비밀번호를 입력해 주세요.');
//             return;
//         }
//
//         // 로그인 폼 제출
//         $('#signupForm').attr('action', '/login').submit();
//     });
//
//     // 회원가입 버튼 클릭 시 이벤트 핸들러
//     $('#signupButton').click(function(event) {
//         // 기본 폼 제출 방지
//         event.preventDefault();
//
//         // 아이디와 비밀번호 유효성 검사
//         const username = $('#username').val();
//         const password = $('#password').val();
//
//         if (username === '' || password === '') {
//             alert('아이디와 비밀번호를 입력해 주세요.');
//             return;
//         }
//
//         // 회원가입 폼 제출
//         $('#signupForm').attr('action', '/signup').submit();
//     });
// });
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('login_button').addEventListener('click', function(event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        if (username === '' || password === '') {
            alert('아이디와 비밀번호를 입력해 주세요.');
            return;
        }

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                username: username,
                password: password
            })
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(error => { throw new Error(error.message); });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message); // 서버 응답 메시지 표시
                if (data.message === "로그인 성공") {
                    localStorage.setItem("currentUser",username);
                    window.location.href = '/chat'; // 로그인 성공 시 리다이렉트
                }
            })
            .catch(error => {
                alert(error.message); // 에러 메시지 표시
            });
    });

    document.getElementById('signupButton').addEventListener('click', function(event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        if (username === '' || password === '') {
            alert('아이디와 비밀번호를 입력해 주세요.');
            return;
        }

        fetch('/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                username: username,
                password: password
            })
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(error => { throw new Error(error.message); });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message); // 서버 응답 메시지 표시
                console.log("test");
                console.log(data);
                if (data.message === "회원가입 성공") {
                    document.getElementById('username').value="";
                    document.getElementById('password').value="";
                    console.log("Hello");

                    //window.location.href = '/'; // 회원가입 성공 시 리다이렉트
                }
            })
            .catch(error => {
                alert(error.message); // 에러 메시지 표시
            });
    });
});