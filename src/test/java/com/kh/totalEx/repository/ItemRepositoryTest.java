package com.kh.totalEx.repository;

import com.kh.totalEx.constant.ItemSellStatus;
import com.kh.totalEx.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장 테스트")
    public void createItemTest(){
//        Item item = new Item();
//        item.setItemNm("테스트 상품");
//        item.setPrice(10000);
//        item.setItemDetail("테스트 상품 상세 설명");
//        item.setItemSellStatus(ItemSellStatus.SELL);
//        item.setStockNumber(100);
//        item.setRegTime(LocalDateTime.now());
//        item.setUpdateTime(LocalDateTime.now());
//        Item saveItem = itemRepository.save(item);
//        System.out.println(saveItem);

        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");
        for (Item e : itemList) {
            System.out.println(e.getItemDetail());
        }
    }

    @Test
    @DisplayName("상품명 또는 상품 상세 설명으로 조회")
    public void findByItemNmOrDetail(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명4");
        for (Item e : itemList) {
            System.out.println("junit 결과 : "+e.getItemNm());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }@Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTestDesc() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품 설명으로 검색")
    public void findBySearchItemDetail(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명2");
        for (Item item : itemList) {
            log.info("검색 결과 : "+item.toString());
        }
    }

    @Test
    @DisplayName("native Query로 상품 설명 검색")
    public void findBySearchItemDetailNative(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명2");
        for (Item item : itemList) {
            log.info("검색 결과 : "+item.toString());
        }
    }

}