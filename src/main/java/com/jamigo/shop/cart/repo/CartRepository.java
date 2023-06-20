package com.jamigo.shop.cart.repo;

import com.jamigo.shop.cart.entity.Cart;
import com.jamigo.shop.cart.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    /**
     * 根據會員編號，取得該會員的購物車資訊
     * @param memberNo 會員編號
     * @return 購物車的完整資訊 (包含參照到的商品資料、以及商品參照到的櫃位資料)
     */
    List<Cart> findByIdMemberNo(Integer memberNo);
    // 為什麼只要這樣寫，就可以取得購物車資訊？  (有一個前提須要知道：Spring Data JPA 會自動幫我們實作 findByXXXXX() 方法 (其中，XXXXX 代表屬性名))

    // 因為購物車有個屬性是：id(複合主鍵)，而 id 這個物件又有個屬性是 memberNo
    // 最後再將方法命名為小駝峰的方式，Spring Data JPA 就會幫我們自動生成 SQL 語句
}
