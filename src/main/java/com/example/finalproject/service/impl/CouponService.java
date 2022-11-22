package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.model.Enum.CouponStatus;
import com.example.finalproject.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.service.ICouponService;
import com.example.finalproject.model.Coupon;

import java.util.List;

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
        return couponRepo.findCouponByName(name).orElseThrow(() -> new NotFoundException("Coupon not found."));
    }

    @Override
    public List<Coupon> getAll() {
        List<Coupon> couponList = couponRepo.findAll();

        if (couponList.isEmpty()) throw new NotFoundException("No coupons registered");

        return couponList;
    }

    @Override
    public void delete(String name) {
        Coupon coupon = this.getByName(name);
        couponRepo.delete(coupon);
    }

    @Override
    public Coupon changeStatusToExpired(String name) {
        Coupon coupon = couponRepo.findCouponByName(name).orElseThrow(() -> new NotFoundException("Coupon not found."));

        coupon.setStatus(CouponStatus.EXPIRADO);

        return couponRepo.save(coupon);
    }
}
