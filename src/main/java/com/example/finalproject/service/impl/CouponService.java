package com.example.finalproject.service.impl;

import com.example.finalproject.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.service.ICouponService;
import com.example.finalproject.model.Coupon;

@Service
public class CouponService implements ICouponService {

    @Autowired
    private CouponRepo couponRepo;

    @Override
    public Coupon create(Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @Override
    public Coupon getByName(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Coupon changeStatus(Long id) {
        return null;
    }
}
