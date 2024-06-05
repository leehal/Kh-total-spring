package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
        // 정렬
        List<Item> findByPriceLessThanOrderByPriceDesc(int price);
        // 검색
        @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
        List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

        @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc ", nativeQuery = true)
        List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
