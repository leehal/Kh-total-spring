package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Cart;
import com.kh.totalEx.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional // 데이터베이스의 논리적인 작업 단위
@TestPropertySource(locations = "classpath:application-test.properties")
class CartRepositoryTest {
    @Autowired // 스프링 컨테이너에서 해당 빈에 해당하는 의존성을 주입 받음
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext // JPA의 Entity Manager를 사용하겠다는 의미 (의존성 주입을 받음)
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMemberInfo() {
        Member member = new Member();
        member.setEmail("jks2024@gmail.com");
        member.setPwd("sphb8250");
        member.setName("곰돌이 사육사");
        member.setRegDate(LocalDateTime.now());
        return member;
    }
    @Test
    @DisplayName("장바구니와 회원 정보 매핑 테스트")
    public void findCartAndMemberTest(){
        Member member = createMemberInfo();
        memberRepository.save(member);
        Cart cart = new Cart();
        cart.setCartName("오늘의 쇼핑");
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush(); // 영속성 컨텍스트에 데이터 저장 후 강제 반영
        em.clear(); // 쓴 후 매니저 클리어 해주기

        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
        Optional<Cart> saveCart1 = cartRepository.findById(cart.getId());
        if(saveCart1.isPresent()){
            Cart testCart = saveCart1.get();
            log.info(testCart.getMember().getEmail());
        }
        log.info(saveCart.getMember().getEmail());
    }
}