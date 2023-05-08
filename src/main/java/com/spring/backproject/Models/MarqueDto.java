package com.spring.backproject.Models;

import lombok.Data;

import java.util.List;

@Data
public class MarqueDto {
    private Integer codmar;
    private String nommar;
    private String profil;
    private String geo;
    private Integer cible;
    private List<Integer> vehidList;
}
