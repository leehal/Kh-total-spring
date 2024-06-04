package com.kh.totalEx.entity;

import com.kh.totalEx.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity // JPA Entity 클래스임을 지정, Entitiy 클래스는 반드시 기본키를 가져야 함.
@ToString
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 생성 전략 : mysql은 자동적으로 숫자가 증가함.
    private Long id; // 상품코드

    @Column(nullable = false , length = 50)
    private String itemNm; // 상품명

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; //재고 수량

    @Lob // 엄청 큰 문자열을 표현 할 때 사용함
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 구매 가능 여부

    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
