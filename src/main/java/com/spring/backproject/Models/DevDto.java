package com.spring.backproject.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class DevDto {

    private Long idDev;
    private String nomDev;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCDC")
    private CDC cdc;



    @Column(name = "NomDLL", length = 50)
    private String nomDll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMenu")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdRC")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdEcu")
    private Ecu ecu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMaj")
    private MAJ maj;


    private Integer idSite;


    private Integer devPrecedent;


    private Boolean devDuplique;


    private Boolean devEnCoursUtilisation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdEtatDev")
    private EtatDev etatDev;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTypeDev")
    private TYPE_DEV typeDev;

    @Column(name = "DevComment", length = 255)
    private String devComment;

    @Column(name = "NumBugzilla", length = 10)
    private String numBugzilla;

    @Column(name = "IdMajPrecedent")
    private Integer idMajPrecedent;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Cables> cablesList=new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Fonction> fonctionList=new ArrayList<>();
    @ManyToMany(fetch  =FetchType.LAZY)
    @JoinTable(name = "dev_vehicule",
            joinColumns = @JoinColumn(name = "id_dev"),
            inverseJoinColumns = @JoinColumn(name = "code_veh"))
    private List<Vehid> vehidList=new ArrayList<>();
    @ManyToMany(fetch  =FetchType.LAZY)
    @JoinTable(name = "Valid_By_DEv",
            joinColumns = @JoinColumn(name = "id_dev"),
            inverseJoinColumns = @JoinColumn(name = "id_valid"))
    private List<Validation> validationList=new ArrayList<>();


}
