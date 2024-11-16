package com.inu.hackerton.SpringProject.repository;

import com.inu.hackerton.SpringProject.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {

}
