package com.example.finalproject.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class APICryptoDTO implements Serializable {
    private String code;
    private String codein;
    private String name;
    private String high;
    private String low;
    private String varBid;
    private String pctChange;
    private Double bid;
    private String ask;
    private String timestamp;
    private String create_date;
}
