package com.jamigo.shop.cart.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * 複合主鍵類別：
 * 1. 實作 Serializable 介面
 */
@Getter
@Setter
@Embeddable
public class CartId implements Serializable {

    private static final long serialVersionUID = 1030209396485882525L;

    // 2. 定義複合主鍵的屬性
    // 會員編號
    private Integer memberNo;
    // 商品編號
    private Integer productNo;

    // 3. 覆寫 equals() 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(memberNo, cartId.memberNo) && Objects.equals(productNo, cartId.productNo);
    }

    // 4. 覆寫 hashCode() 方法
    @Override
    public int hashCode() {
        return Objects.hash(memberNo, productNo);
    }
}
