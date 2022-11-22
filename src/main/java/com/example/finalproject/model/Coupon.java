package com.example.finalproject.model;

import com.example.finalproject.model.Enum.Category;
import com.example.finalproject.model.Enum.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 25)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Max(value = 100)
    private int discount;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private CouponStatus status;
}
