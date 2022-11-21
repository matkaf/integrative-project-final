package com.example.finalproject.controller;

import com.example.finalproject.dto.APICryptoDTO;
import com.example.finalproject.dto.PurchaseOrderDTO;
import com.example.finalproject.service.ICryptoQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fresh-products")
public class CryptoQuotationController {

    @Autowired
    private ICryptoQuotation cryptoService;

    @GetMapping("/purchase/crypto/{purchaseId}")
    public ResponseEntity<List<Object>> getQuotationByPurchaseOrder(@PathVariable Long purchaseId) {
        PurchaseOrderDTO purchaseOrder = PurchaseOrderDTO.convertToResponse(cryptoService.getPurchaseOrderById(purchaseId));

        System.out.println("========== -> " + purchaseOrder.getTotalPrice());

        List<Object> response = cryptoService.getQuotationByPurchaseOrder();

        System.out.println("============ -> RESPONSE: " + response);

        return ResponseEntity.ok(response);
    }
}
