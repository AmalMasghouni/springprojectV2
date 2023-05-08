package com.spring.backproject.Models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CdcDto {
    private Long idCDC;
    private String nomCDC;
    private String refCDC;
    private String indCDC;
    private String  idSite;
    private Timestamp creation;
    private Boolean signatureOk;
    private String refCDCArdia;
}
