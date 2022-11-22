package com.example.finalproject.service;

import com.example.finalproject.dto.APICryptoDTO;
import com.example.finalproject.model.PurchaseOrder;

import java.util.List;

public interface ICryptoQuotation {

    PurchaseOrder getPurchaseOrderById(Long purchaseId);
    List<APICryptoDTO> getQuotationByPurchaseOrder();
}
