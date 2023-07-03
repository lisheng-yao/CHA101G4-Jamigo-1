package com.jamigo.shop.wishlist.entity;

public class ProductVO {
    private Integer productNo;
    private Integer counterNo;
    private String counterName;
    private String productName;
    private Integer productPrice;
    private String productInfo;
    private Integer productSaleNum;
    private Integer evalTotalPeople;
    private Integer evalTotalScore;

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

    public Integer getCounterNo() {
        return counterNo;
    }

    public void setCounterNo(Integer counterNo) {
        this.counterNo = counterNo;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Integer getProductSaleNum() {
        return productSaleNum;
    }

    public void setProductSaleNum(Integer productSaleNum) {
        this.productSaleNum = productSaleNum;
    }

    public Integer getEvalTotalPeople() {
        return evalTotalPeople;
    }

    public void setEvalTotalPeople(Integer evalTotalPeople) {
        this.evalTotalPeople = evalTotalPeople;
    }

    public Integer getEvalTotalScore() {
        return evalTotalScore;
    }

    public void setEvalTotalScore(Integer evalTotalScore) {
        this.evalTotalScore = evalTotalScore;
    }


}
