package com.inu.hackerton.SpringProject.service;

import com.inu.hackerton.SpringProject.model.Test;
import com.inu.hackerton.SpringProject.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  // 이 클래스가 서비스 레이어에 속하며, Spring의 Bean으로 등록됨을 명시
@RequiredArgsConstructor  // final 필드인 testRepository에 대해 생성자를 자동 생성해 의존성을 주입
public class TestService {

    private final TestRepository testRepository;  // 데이터베이스와 상호작용하기 위해 사용하는 TestRepository 인터페이스

    // 새로운 Test 객체를 생성(저장)하는 메서드
    public Test create(Test test) {
        return testRepository.save(test);  // Test 객체를 데이터베이스에 저장하고 저장된 객체를 반환
    }

    // 주어진 ID를 통해 Test 객체를 조회하는 메서드
    public Optional<Test> read(Long id) {
        return testRepository.findById(id);  // ID에 해당하는 Test 객체를 Optional로 반환 (없을 경우 비어 있음)
    }

    // 기존의 Test 객체를 업데이트하는 메서드
    public Optional<Test> update(Test test) {
        return testRepository
                .findById(test.getId())  // ID를 통해 기존 Test 객체를 조회
                .map((savedTest) -> {  // 객체가 존재하는 경우 업데이트 수행
                    savedTest.setName(test.getName());  // 이름 필드를 업데이트
                    savedTest.setEmail(test.getEmail());  // 이메일 필드를 업데이트
                    return testRepository.save(savedTest);  // 업데이트된 객체를 저장하고 반환
                });
    }

    // 주어진 ID를 통해 Test 객체를 삭제하는 메서드
    public void delete(Long id) {
        testRepository.deleteById(id);  // ID에 해당하는 Test 객체를 데이터베이스에서 삭제
    }
}