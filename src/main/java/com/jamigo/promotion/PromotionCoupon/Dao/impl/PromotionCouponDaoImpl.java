//package com.jamigo.promotion.PromotionCoupon.Dao.impl;
//
//import com.jamigo.promotion.PromotionCoupon.Dao.PromotionCouponDao;
//import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//
//@Repository
//public class PromotionCouponDaoImpl implements PromotionCouponDao {
//
//    @Override
//    public List<PromotionCoupon> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<PromotionCoupon> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<PromotionCoupon> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<PromotionCoupon> findAllById(Iterable<Integer> integers) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//
//    }
//
//    @Override
//    public void delete(PromotionCoupon entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Integer> integers) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends PromotionCoupon> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public <S extends PromotionCoupon> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<PromotionCoupon> findById(Integer integer) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends PromotionCoupon> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<PromotionCoupon> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public PromotionCoupon getOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public PromotionCoupon getById(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public PromotionCoupon getReferenceById(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends PromotionCoupon> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends PromotionCoupon> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends PromotionCoupon, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//}
