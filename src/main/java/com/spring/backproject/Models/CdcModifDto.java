package com.spring.backproject.Models;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class CdcModifDto {
    private Long idCDC;
    private String nomCDC;
    private String refCDC;
    private String indCDC;
    private String idSite;
    private Timestamp creation;
    private String signatureOk;
    private String refCDCArdia;
}
