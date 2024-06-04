package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface ItemRepository extends JpaRepository<Item, Long> {
        List<Item> findByItemNm(String itemNm);
        List<Item> findByItemNmAndPrice(String itemNm, int price);
        // Or 조건처리
        List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
        // LessThan : 보다 작다라는 의미
        List<Item> findByPriceLessThan(int price);

        List<Item> findByPriceLessThanOrderByPriceDesc(int price);
}
