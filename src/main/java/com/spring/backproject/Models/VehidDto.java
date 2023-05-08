package com.spring.backproject.Models;

import lombok.Data;

import java.util.Date;

@Data
public class VehidDto {
    private String nomVeh;
    private String nomInterne;
    private Integer codeVeh;
    private String marque;
    private Boolean testGlobal;
    private String msgDiag;
    private String grpMarq;
    private String fro;
    private String t;
    private String remarque ;
    private Date upd;
    private Boolean onlyElec;
    private Boolean avertissElec ;
    private Boolean avertissHybrid;
}
