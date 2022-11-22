package com.example.finalproject.service;

import com.example.finalproject.model.Coupon;

public interface ICouponService {

    Coupon create(Coupon coupon);

    Coupon getByName(String name);

    void delete(Long id);

    Coupon changeStatus(Long id);
}
