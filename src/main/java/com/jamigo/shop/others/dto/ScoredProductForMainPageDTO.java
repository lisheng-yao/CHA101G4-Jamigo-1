package com.jamigo.shop.others.dto;

public class ScoredProductForMainPageDTO {
    private ProductForMainPageDTO product;
    private float score;

    public ScoredProductForMainPageDTO(ProductForMainPageDTO product, float score) {
        this.product = product;
        this.score = score;
    }

    public ProductForMainPageDTO getProduct() {
        return product;
    }

    public float getScore() {
        return score;
    }
}