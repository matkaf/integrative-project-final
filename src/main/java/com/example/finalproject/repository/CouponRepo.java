package com.example.finalproject.repository;

import com.example.finalproject.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
}
