package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 생성")
    public void createTest(){
        for (int i = 0; i <10; i++) {
            Member member = new Member();
            member.setEmail("pkmm@naver.com"+i);
            member.setPwd("1234");
            member.setRegDate(LocalDateTime.now());
            member.setName("pkpk"+i);
//            member.setImage(null);
            memberRepository.save(member);

        }
    }

    @Test
    @DisplayName("개별 회원 조회")
    public void memberOneSelect(){
        this.createTest();
        Optional<Member> member = memberRepository.findByEmail("pkmm@naver.com2");
        System.out.println(member.get().getName());
    }
    @Test
    @DisplayName("전체 회원 조회")
    public void memberAllSelect(){
        this.createTest();
        List<Member> list = memberRepository.findAll();
        for (Member member : list) {
            System.out.println(member);
        }
    }

    @Test
    @DisplayName("로그인 체크")
    public void loginCheck(){
        this.createTest();
        Optional<Member> check = memberRepository.findByEmailAndPwd("pkmm@naver.com2","1234");
        System.out.println("로그인여부 : "+check.isPresent());
    }
    @Test
    @DisplayName("가입여부 확인")
    public void isMember(){
        this.createTest();
        Optional<Member> check = memberRepository.findByEmail("pkmm@naver.com2");
        System.out.println("가입여부 : "+check.isPresent());
    }
}