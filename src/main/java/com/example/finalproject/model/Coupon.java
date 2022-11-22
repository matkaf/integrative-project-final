package com.example.finalproject.model;

import com.example.finalproject.model.Enum.Category;
import com.example.finalproject.model.Enum.CouponStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int discount;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private CouponStatus status;
}
