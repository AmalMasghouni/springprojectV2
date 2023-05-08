package com.spring.backproject.Models;

import lombok.Data;

@Data
public class EtatValidationDto {
    private String N;
    private String NomDev;
    private String Etat;
    private String RefCDC;
    private String NomCDC;
    private String Site;
    private String Developpeur;
    private String Testunitaire;
    private String Testintegration;
}
