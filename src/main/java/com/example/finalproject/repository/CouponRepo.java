package com.example.finalproject.repository;

import com.example.finalproject.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findCouponByName(String name);
}
