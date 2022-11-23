package com.example.finalproject.controller;

import com.example.finalproject.dto.PurchaseOrderCreateDTO;
import com.example.finalproject.dto.PurchaseOrderDTO;
import com.example.finalproject.dto.PurchaseOrderUpdateDTO;
import com.example.finalproject.exception.PurchaseFailureException;
import com.example.finalproject.model.Coupon;
import com.example.finalproject.model.Enum.CouponStatus;
import com.example.finalproject.model.PurchaseOrder;
import com.example.finalproject.service.ICouponService;
import com.example.finalproject.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class PurchaseOrderController {

    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    @Autowired
    private ICouponService couponService;

    @PostMapping("/orders")
    public ResponseEntity<String> createPurchaseOrder(@Valid @RequestBody PurchaseOrderCreateDTO purchaseOrderCreateDTO) {
        PurchaseOrder purchaseOrder = PurchaseOrderCreateDTO.convertToPurchaseOrder(purchaseOrderCreateDTO);
        return new ResponseEntity<>("Total price: " + purchaseOrderService.createPurchaseOrder(purchaseOrder), HttpStatus.CREATED);
    }

    @GetMapping("/orders/{purchaseCode}")
    public ResponseEntity<PurchaseOrderDTO> findAllAdvertisementsByPurchase(@PathVariable Long purchaseCode) {
        return new ResponseEntity<>(PurchaseOrderDTO.convertToResponse(purchaseOrderService.findAllAdvertisementsByPurchase(purchaseCode)), HttpStatus.OK);
    }

    @PutMapping("/orders/{purchaseCode}")
    public ResponseEntity<PurchaseOrderUpdateDTO> updatePurchaseStatus(@PathVariable Long purchaseCode,
                                                                       @RequestParam(name = "coupon") String coupon) {

        if (coupon != null) {
            Coupon activeCoupon = couponService.getByName(coupon);

            if (activeCoupon.getStatus() == CouponStatus.EXPIRADO) {
                throw new PurchaseFailureException("This coupon has expired. Please try another one!");
            }

            PurchaseOrderUpdateDTO purchaseOrder = PurchaseOrderUpdateDTO.convertToResponse(purchaseOrderService.updatePurchaseStatus(purchaseCode));


            Double totalPrice = purchaseOrder.getTotalPrice();
            Double discount = (double) (activeCoupon.getDiscount() / 100.);

            Double newPrice = totalPrice - (discount * totalPrice);

            purchaseOrder.setTotalPrice(newPrice);
            return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
        } else {
            PurchaseOrderUpdateDTO purchaseOrder = PurchaseOrderUpdateDTO.convertToResponse(purchaseOrderService.updatePurchaseStatus(purchaseCode));

            return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
        }
    }
}
