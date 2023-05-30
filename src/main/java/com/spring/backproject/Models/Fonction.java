package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFonction;
    private String nomFonction;
    private Integer ordre;
    private Integer codeDico;
    private String DescrAnglais;
    @ManyToMany(mappedBy = "fonctionList",fetch = FetchType.LAZY)
    private List<DEV> DEVList =new ArrayList<>();
    @ManyToMany(mappedBy = "fonctionList",fetch = FetchType.LAZY)
    private List<CDC>cdcList=new ArrayList<>();
}
