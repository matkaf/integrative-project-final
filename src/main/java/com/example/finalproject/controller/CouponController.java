package com.example.finalproject.controller;

import com.example.finalproject.model.Coupon;
import com.example.finalproject.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @PostMapping("/coupon")
    public ResponseEntity<Coupon> create(@Valid @RequestBody Coupon coupon) {
        return new ResponseEntity<>(couponService.create(coupon), HttpStatus.CREATED);
    }

    @GetMapping("/coupon/{name}")
    public ResponseEntity<Coupon> getByName(@PathVariable String name) {
        return new ResponseEntity<>(couponService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/coupon")
    public ResponseEntity<List<Coupon>> getAll() {
        return new ResponseEntity<>(couponService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/coupon/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        couponService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/coupon/{name}")
    public ResponseEntity<Coupon> changeStatusToExpired(@PathVariable String name) {
        return new ResponseEntity<>(couponService.changeStatusToExpired(name), HttpStatus.OK);
    }
}
