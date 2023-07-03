package com.jamigo.shop.category.entity;

public class CategoryVO {

    private int productCatNo ;

    private String productCatName;

    private String productCatPerson;

    private Integer productCount;





    public int getProductCatNo() {
        return productCatNo;
    }

    public void setProductCatNo(int productCatNo) {
        this.productCatNo = productCatNo;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public String getProductCatPerson() {
        return productCatPerson;
    }

    public void setProductCatPerson(String productCatPerson) {
        this.productCatPerson = productCatPerson;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
