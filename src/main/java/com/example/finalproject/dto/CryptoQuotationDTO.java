package com.example.finalproject.dto;

import com.example.finalproject.model.PurchaseItem;
import lombok.Data;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class CryptoQuotationDTO {

    private String coinName;

    private String coinCode;

    private Double quotation;

    private Double totalPrice;

    CryptoQuotationDTO(APICryptoDTO crypto, Double purchasePrice) {
        this.coinName = crypto.getName();
        this.coinCode = crypto.getCode();
        this.quotation = crypto.getBid();
        this.totalPrice = purchasePrice / crypto.getBid();
    }
    public static CryptoQuotationDTO convertToResponse(APICryptoDTO crypto, Double purchasePrice) {
        return new CryptoQuotationDTO(crypto, purchasePrice);
    }

    public static List<CryptoQuotationDTO> convertListToResponse(List<APICryptoDTO> cryptoList, Double purchasePrice) {
        return cryptoList.stream().map( e -> CryptoQuotationDTO.convertToResponse(e, purchasePrice)).collect(Collectors.toList());
    }

}
