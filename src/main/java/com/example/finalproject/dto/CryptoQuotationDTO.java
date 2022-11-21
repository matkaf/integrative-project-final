package com.example.finalproject.dto;

import com.example.finalproject.model.PurchaseItem;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CryptoQuotationDTO {

    private String coinName;

    private String coinCode;

    private Double quotation;

    private Double totalPrice;

    CryptoQuotationDTO(APICryptoDTO crypto) {
        this.coinName = crypto.getName();
        this.coinCode = crypto.getCode();
        this.quotation = crypto.getBid();
    }
    public static CryptoQuotationDTO convertToResponse(APICryptoDTO crypto) {
        return new CryptoQuotationDTO(crypto);
    }
    public static List<CryptoQuotationDTO> convertListToResponse(List<APICryptoDTO> cryptoList) {
        return cryptoList.stream().map(CryptoQuotationDTO::convertToResponse).collect(
                Collectors.toList());
    }
}
