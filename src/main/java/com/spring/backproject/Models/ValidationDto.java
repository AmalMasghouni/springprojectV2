package com.spring.backproject.Models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
 @Data
public class ValidationDto {
    private Integer idValid;


    private String typeValid;


    private String DateValid;


    private String EtatValid;

    private String idVer;
    @ManyToMany(mappedBy = "validationList",fetch = FetchType.LAZY)
    private List<DEV> DEVList =new ArrayList<>();
}
