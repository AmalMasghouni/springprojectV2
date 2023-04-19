package com.spring.backproject.Models;

import lombok.Data;

@Data
public class VehidDto {
    private String nomVeh;
    private String nomInterne;
    private Marque marque;
    private Boolean testGlobal;
    private String msgDiag;
    private String grpMarq;
    private String fro;
    private String t;
    private Boolean onlyElec;
    private Boolean avertissElec ;
}
