package com.example.finalproject.service.impl;

import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.exception.PurchaseFailureException;
import com.example.finalproject.model.Coupon;
import com.example.finalproject.model.Enum.CouponStatus;
import com.example.finalproject.model.Enum.OrderStatus;
import com.example.finalproject.repository.CouponRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @InjectMocks
    CouponService couponService;

    @Mock
    CouponRepo couponRepo;

    private Coupon coupon;
    private Coupon coupon_one;

    private Coupon coupon_two;

    private List<Coupon> couponList;

    @BeforeEach
    void setup() {
        couponList = new ArrayList<>();

        coupon = coupon.builder()
                .id(1L)
                .name("MELIFRIDAY10")
                .discount(10)
                .status(CouponStatus.ATIVO)
                .build();

        couponList.add(coupon);

        coupon_one = coupon.builder()
                .id(2L)
                .name("NATAL15")
                .discount(15)
                .status(CouponStatus.ATIVO)
                .build();

        couponList.add(coupon_one);

        coupon_two = coupon.builder()
                .id(3L)
                .name("DEGRACA")
                .discount(100)
                .status(CouponStatus.EXPIRADO)
                .build();

        couponList.add(coupon_two);
    }

    @Test
    void create_returnCoupon_whenValidAttributes() {
        couponService.create(coupon);
        verify(couponRepo, times(1)).save(coupon);
    }

    @Test
    void updateCouponStatusToExpired_returnUpdatedCoupon_whenCouponExists() {
        when(couponRepo.findCouponByName(any())).thenReturn(Optional.ofNullable(coupon));
        couponService.changeStatusToExpired(coupon.getName());
        assertEquals(coupon.getStatus(), CouponStatus.EXPIRADO);
        verify(couponRepo, times(1)).save(coupon);
    }

    @Test
    void getCoupon_returnCoupon_whenProvidedTheRightName() {
        when(couponRepo.findCouponByName(any())).thenReturn(Optional.ofNullable(coupon));

        Coupon response = couponService.getByName(coupon.getName());

        assertEquals(coupon.getName(), response.getName());
        verify(couponRepo, times(1)).findCouponByName(coupon.getName());
    }

    @Test
    void getAllCoupons_returnListOfCoupon_whenSuccess() {
        when(couponRepo.findAll()).thenReturn(couponList);

        List<Coupon> response = couponService.getAll();

        assertEquals(couponList.size(), response.size());
        assertFalse(response.isEmpty());
        verify(couponRepo, times(1)).findAll();
    }

    @Test
    void getAllCoupons_throwsNotFoundException_whenNoCouponsExists() {
        when(couponRepo.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> couponService.getAll());
        verify(couponRepo, times(1)).findAll();
    }

    @Test
    void deleteCoupon_whenCouponNameExists() {
        when(couponRepo.findCouponByName(any())).thenReturn(Optional.ofNullable(coupon_two));

        couponService.delete(coupon_two.getName());

        verify(couponRepo, times(1)).delete(coupon_two);
    }
}
