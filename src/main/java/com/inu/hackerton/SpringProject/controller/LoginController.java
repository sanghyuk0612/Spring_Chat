//package com.inu.hackerton.SpringProject.controller;
//
//import com.inu.hackerton.SpringProject.model.User;
//import com.inu.hackerton.SpringProject.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class LoginController {
//
//    private final UserRepository userRepository;
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        // 로그인 로직 구현 (예: 사용자 인증)
//        boolean isAuthenticated = authenticateUser(username, password);
//        System.out.println(username + "로그인 버튼" + password);
//
//        // 사용자 조회
//        User user = userRepository.findByUsername(username).orElse(null);
//
//        // 사용자가 존재하고 비밀번호가 일치하는지 확인
//        if (user != null && password.equals( user.getPassword())) {
//            return "redirect:/home";
//        } else {
//            return "redirect:/login?error"; // 로그인 실패 시 에러 메시지와 함께 리다이렉트
//        }
//    }
//    @PostMapping("/signup")
//    public String signup(@RequestParam String username, @RequestParam String password) {
//        // 회원가입 로직 구현 (예: 사용자 정보 저장)
//        registerUser(username, password);
//        System.out.println(username + "회원가입버튼" + password);
//        User newUser = new User();
//        newUser.setUsername(username)
//               .setPassword(password);
//        userRepository.save(newUser);
//        return "redirect:/index"; // 회원가입 후 로그인 페이지로 리다이렉트
//    }
//
//    private boolean authenticateUser(String username, String password) {
//        // 사용자 인증 로직
//        userRepository.findByUsername(username);
//        System.out.println();
//
//        return true; // 인증 성공 (예시)
//    }
//
//    private void registerUser(String username, String password) {
//        // 사용자 등록 로직
//    }
//}
package com.inu.hackerton.SpringProject.controller;

import com.inu.hackerton.SpringProject.model.User;
import com.inu.hackerton.SpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null && password.equals(user.getPassword())) {
            return ResponseEntity.ok(new ResponseMessage("로그인 성공", HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseMessage("로그인 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED));
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signup(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseMessage("회원가입 실패: 사용자 이름이 이미 존재합니다.", HttpStatus.CONFLICT));
        }

        User newUser = new User();
        newUser.setUsername(username)
                .setPassword(password);
        userRepository.save(newUser);

        return ResponseEntity.ok(new ResponseMessage("회원가입 성공", HttpStatus.OK));
    }

    public static class ResponseMessage {
        private String message;
        private HttpStatus status;

        public ResponseMessage(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }
    }
}