package com.example.finalproject.service.impl;

import com.example.finalproject.dto.APICryptoDTO;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.exception.PurchaseFailureException;
import com.example.finalproject.model.Enum.OrderStatus;
import com.example.finalproject.model.PurchaseOrder;
import com.example.finalproject.repository.PurchaseOrderRepo;
import com.example.finalproject.service.ICryptoQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CryptoQuotationService implements ICryptoQuotation {

    @Autowired
    private PurchaseOrderRepo purchaseRepo;

    @Override
    public PurchaseOrder getPurchaseOrderById(Long purchaseId) {
        PurchaseOrder purchaseOrder = purchaseRepo.findById(purchaseId)
                .orElseThrow(() -> new NotFoundException("Purchase Order not found!"));

       OrderStatus status = purchaseOrder.getOrderStatus();
       if (status.equals(OrderStatus.FINALIZADO)) throw new PurchaseFailureException("This purchase order was already paid");

       return purchaseOrder;
    }

    @Override
    public List<Object> getQuotationByPurchaseOrder() {
        String cryptoList = "BTC-BRL,ETH-BRL,LTC-BRL";

        Object[] response = new RestTemplate()
                .getForEntity("https://economia.awesomeapi.com.br/json/last/" + cryptoList, Object[].class)
                .getBody();

       if (response == null) throw new NotFoundException("The quotation list is not available");


       return Arrays.asList(response);
    }
}
