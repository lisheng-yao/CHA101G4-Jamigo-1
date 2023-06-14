package com.jamigo.shop.cart.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CartId implements Serializable {

    private static final long serialVersionUID = 1030209396485882525L;

    private Integer memberNo;
    private Integer productNo;

    // getters, setters, equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(memberNo, cartId.memberNo) && Objects.equals(productNo, cartId.productNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, productNo);
    }
}
