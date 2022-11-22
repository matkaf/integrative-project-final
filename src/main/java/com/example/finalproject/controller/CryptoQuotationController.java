package com.example.finalproject.controller;

import com.example.finalproject.dto.APICryptoDTO;
import com.example.finalproject.dto.CryptoQuotationDTO;
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
    public ResponseEntity<List<CryptoQuotationDTO>> getQuotationByPurchaseOrder(@PathVariable Long purchaseId) {
        PurchaseOrderDTO purchaseOrder = PurchaseOrderDTO.convertToResponse(cryptoService.getPurchaseOrderById(purchaseId));
        Double purchasePrice = purchaseOrder.getTotalPrice();

        System.out.println("========== -> " + purchaseOrder.getTotalPrice());

        List<APICryptoDTO> response = cryptoService.getQuotationByPurchaseOrder();

        /**
         * retornar uma Lista de CryptoQuotationDTO
         * [{
         *     coinName: Bitcoin,
         *     coinCode: BTC,
         *     quotation: 84.598,
         *     totalPrice: ex -> 140/84.598 = 0.00165488545
         * },
         * {
         *     coinName: Ethereum,
         *     coinCode: ETH,
         *     quotation: 5.845,
         *     totalPrice: ex -> 140/5.845 = 0.0239
         * }]
         */

        return ResponseEntity.ok(CryptoQuotationDTO.convertListToResponse(response, purchasePrice));
    }
}
