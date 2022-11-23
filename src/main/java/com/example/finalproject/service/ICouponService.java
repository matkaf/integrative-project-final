package com.example.finalproject.service;

import com.example.finalproject.model.Coupon;

import java.util.List;

public interface ICouponService {

    Coupon create(Coupon coupon);

    Coupon getByName(String name);

    List<Coupon> getAll();

    void delete(String name);

    Coupon changeStatusToExpired(String name);
}
