package com.inu.hackerton.SpringProject.controller;

import com.inu.hackerton.SpringProject.model.Test;
import com.inu.hackerton.SpringProject.model.TestResponse;
import com.inu.hackerton.SpringProject.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController  // 이 클래스가 REST 컨트롤러로 작동함을 명시
@RequestMapping("/test-api")  // 이 컨트롤러의 모든 요청 URL이 "/test-api"로 시작되도록 설정
@RequiredArgsConstructor  // final 필드인 testService에 대해 생성자를 자동 생성해 의존성을 주입
public class TestController {

    private final TestService testService;  // TestService 인스턴스를 주입받아 사용

    /*
    새로운 Test 엔티티를 생성하는 POST 요청을 처리
    예시: curl -X POST http://localhost:8080/test-api/test -H "Content-Type: application/json" -d "{\"name\":\"A\", \"email\":\"B\"}"

    */
    @PostMapping("/test")
    public TestResponse create(@RequestBody Test test) {
        Test ret = testService.create(test);  // Test 객체를 생성(저장)하고 반환받음
        return new TestResponse(ret.getId(), ret.getName());  // id와 name을 포함한 TestResponse 객체 반환
    }

    /*
    ID를 기반으로 Test 엔티티를 조회하는 GET 요청을 처리
    예시: curl -X GET 'http://localhost:8080/test-api/test?id=1'
    */
    @GetMapping("/test")
    public TestResponse read(@RequestParam Long id) {
        Optional<Test> ret = testService.read(id);  // ID에 해당하는 Test 객체를 조회
        return ret
                .map(it -> new TestResponse(it.getId(), it.getName()))  // 조회된 객체가 있다면 TestResponse로 변환하여 반환
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));  // 없으면 404 예외 발생
    }

    /*
    Test 엔티티를 업데이트하는 PUT 요청을 처리
    curl -X PUT http://localhost:8080/test-api/test -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"Updated Name\", \"email\":\"updated@example.com\"}"

    */
    @PutMapping("/test")
    public TestResponse update(@RequestBody Test test) {
        Optional<Test> ret = testService.update(test);  // Test 객체를 업데이트하고 반환받음
        return ret
                .map(it -> new TestResponse(it.getId(), it.getName()))  // 업데이트된 객체가 있다면 TestResponse로 변환하여 반환
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));  // 없으면 404 예외 발생
    }

    /*
    ID를 기반으로 Test 엔티티를 삭제하는 DELETE 요청을 처리
    curl -X DELETE "http://localhost:8080/test-api/test?id=1"
    */
    @DeleteMapping("/test")
    public void delete(@RequestParam Long id) {
        testService.delete(id);  // ID에 해당하는 Test 객체를 삭제
    }
}