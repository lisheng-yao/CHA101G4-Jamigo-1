package com.jamigo.shop.others.service.impl;

import com.jamigo.counter.counter.dao.CounterRepository;
import com.jamigo.shop.others.dto.ProductForMainPageDTO;
import com.jamigo.shop.others.repo.ProductForMainPageRepository;
import com.jamigo.shop.others.service.LuceneService;
import com.jamigo.shop.others.service.MainPageService;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.entity.ProductCategory;
import com.jamigo.shop.product.repo.ProductCategoryRepository;
import com.jamigo.shop.product.repo.ProductRepository;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    private ProductForMainPageRepository productForMainPageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private LuceneService luceneService;

    @Override
    public List<ProductForMainPageDTO> getRecommendation() {

        List<Product> productList = productForMainPageRepository.getRandomProducts(5);

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

        for (var product : productList) {

            ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
            productForMainPageDTO.setProductNo(product.getProductNo());
            productForMainPageDTO.setProductName(product.getProductName());
            productForMainPageDTO.setProductPrice(product.getProductPrice());
            productForMainPageDTO.setCounterNo(product.getCounterNo());

            counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

            productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
            productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());

            productForMainPageDTOList.add(productForMainPageDTO);
        }

        return productForMainPageDTOList;
    }

    @Override
    public List<ProductForMainPageDTO> getCounterRecommendation(Integer counterNo) {
        List<Product> productList = productForMainPageRepository.getRandomCounterProducts(counterNo, 5);

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

        for (var product : productList) {

            ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
            productForMainPageDTO.setProductNo(product.getProductNo());
            productForMainPageDTO.setProductName(product.getProductName());
            productForMainPageDTO.setProductPrice(product.getProductPrice());
            productForMainPageDTO.setCounterNo(product.getCounterNo());

            counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

            productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
            productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());

            productForMainPageDTOList.add(productForMainPageDTO);
        }

        return productForMainPageDTOList;
    }

    @Override
    public List<ProductForMainPageDTO> getBestSelling() {

        List<Product> productList = productForMainPageRepository.getBestSellingProducts();

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

        for (var product : productList) {

            ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
            productForMainPageDTO.setProductNo(product.getProductNo());
            productForMainPageDTO.setProductName(product.getProductName());
            productForMainPageDTO.setProductPrice(product.getProductPrice());
            productForMainPageDTO.setCounterNo(product.getCounterNo());

            counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

            productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
            productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());

            productForMainPageDTOList.add(productForMainPageDTO);
        }

        return productForMainPageDTOList;
    }

    public List<ProductCategory> getAllProductCategory() {

        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(Integer productCatNo) {

        return productCategoryRepository.findById(productCatNo).orElse(null);
    }

    @Override
    public List<ProductForMainPageDTO> getProductsByCategory(Integer productCatNo, Integer pageNum, Integer orderBy) {

        Sort sortMethod = null;
        ProductCategory productCategory = productCategoryRepository.findById(productCatNo).orElse(null);

        switch(orderBy) {
            case 1:
                sortMethod = Sort.by("productNo");
                break;
            case 2:
                sortMethod = Sort.by("productSaleNum").descending();
                break;
            case 3:
                sortMethod = Sort.by("productName");
                break;
            case 4:
                sortMethod = Sort.by("productPrice");
                break;
            case 5:
                sortMethod = Sort.by("productPrice").descending();
                break;
        }

        if (sortMethod != null && productCategory != null) {

            Pageable pageable = PageRequest.of(pageNum-1, 12, sortMethod);
            Page<Product> page = productForMainPageRepository.findByProductCategory(productCategory, pageable);
            List<Product> productList = page.getContent(); // 取得當頁的內容

            List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

            for (var product : productList) {

                ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
                productForMainPageDTO.setProductNo(product.getProductNo());
                productForMainPageDTO.setProductName(product.getProductName());
                productForMainPageDTO.setProductPrice(product.getProductPrice());
                productForMainPageDTO.setProductInfo(product.getProductInfo());
                productForMainPageDTO.setCounterNo(product.getCounterNo());

                counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

                productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
                productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());

                if (product.getProductStat())
                    productForMainPageDTOList.add(productForMainPageDTO);
            }

            return productForMainPageDTOList;
        }

        return null;
    }

    @Override
    public Integer getProductAmountByCategory(Integer productCatNo) {

        return productForMainPageRepository.getProductAmountByCategory(productCatNo);
    }

    @Override
    public List<ProductForMainPageDTO> searchProducts(String keyword, Integer orderBy) {
        List<Product> allProducts = productRepository.findAll();
        Map<Product, Integer> lcsMap = new HashMap<>();
        keyword = keyword.toLowerCase();

        for (Product product : allProducts) {
            int lcsLength = getLCSLength(product.getProductName().toLowerCase(), keyword);
            if (lcsLength > 1) {
                lcsMap.put(product, lcsLength);
            }
        }

        // Sort and filter products by the length of LCS in descending order
        List<Product> sortedProducts = new ArrayList<>(lcsMap.keySet());
        sortedProducts.sort((p1, p2) -> lcsMap.get(p2) - lcsMap.get(p1));

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

        for (Product product : sortedProducts) {
            if (product.getProductStat()) {
                ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
                productForMainPageDTO.setProductNo(product.getProductNo());
                productForMainPageDTO.setProductName(product.getProductName());
                productForMainPageDTO.setProductPrice(product.getProductPrice());
                productForMainPageDTO.setProductInfo(product.getProductInfo());
                productForMainPageDTO.setCounterNo(product.getCounterNo());

                counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

                productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
                productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());
                productForMainPageDTOList.add(productForMainPageDTO);
            }
        }

        Map<Integer, Comparator<ProductForMainPageDTO>> comparatorMap = new HashMap<>();
        comparatorMap.put(1, Comparator.comparingInt(ProductForMainPageDTO::getProductNo));
        comparatorMap.put(3, Comparator.comparing(ProductForMainPageDTO::getProductName));
        comparatorMap.put(4, Comparator.comparingInt(ProductForMainPageDTO::getProductPrice));
        comparatorMap.put(5, Comparator.comparingInt(ProductForMainPageDTO::getProductPrice).reversed());

        Comparator<ProductForMainPageDTO> comparator = comparatorMap.get(orderBy);
        if (comparator != null) {
            productForMainPageDTOList.sort(comparator);
        }

        return productForMainPageDTOList;
    }

    @Override
    public int getLCSLength(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    @Override
    public List<ProductForMainPageDTO> betterSearchProducts(String keyword, Integer orderBy) throws IOException, ParseException {
        luceneService.clearIndex();

        List<Product> allProducts = productRepository.findAll();

        // Build index with Lucene
        for (Product product : allProducts) {
            luceneService.indexDocument(product.getProductNo().toString(), product.getProductName().toLowerCase());
        }

        // Search with Lucene
        TopDocs topDocs = luceneService.searchIndex(keyword.toLowerCase());
        List<Product> searchedProducts = new ArrayList<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = luceneService.getDocument(scoreDoc.doc);

            Product product = productRepository.findById(Integer.parseInt(doc.get("id"))).orElse(null);

            if (product != null)
                searchedProducts.add(product);
        }

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();
        for (Product product : searchedProducts) {
            if (product.getProductStat()) {
                ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
                productForMainPageDTO.setProductNo(product.getProductNo());
                productForMainPageDTO.setProductName(product.getProductName());
                productForMainPageDTO.setProductPrice(product.getProductPrice());
                productForMainPageDTO.setProductInfo(product.getProductInfo());
                productForMainPageDTO.setCounterNo(product.getCounterNo());

                counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

                productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
                productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());
                productForMainPageDTOList.add(productForMainPageDTO);
            }
        }

        Map<Integer, Comparator<ProductForMainPageDTO>> comparatorMap = new HashMap<>();
        comparatorMap.put(1, Comparator.comparingInt(ProductForMainPageDTO::getProductNo));
        comparatorMap.put(3, Comparator.comparing(ProductForMainPageDTO::getProductName));
        comparatorMap.put(4, Comparator.comparingInt(ProductForMainPageDTO::getProductPrice));
        comparatorMap.put(5, Comparator.comparingInt(ProductForMainPageDTO::getProductPrice).reversed());

        Comparator<ProductForMainPageDTO> comparator = comparatorMap.get(orderBy);
        if (comparator != null) {
            productForMainPageDTOList.sort(comparator);
        }

        return productForMainPageDTOList;
    }
}
