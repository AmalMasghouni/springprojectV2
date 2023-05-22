package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEV")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DEV {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdDev")
    private Long idDev;

    @Column(name = "NomDev", length = 70)
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

    @Column(name = "IdSite")
    private Integer idSite;

    @Column(name = "DevPrecedent")
    private Integer devPrecedent;

    @Column(name = "DevDuplique")
    private Boolean devDuplique;

    @Column(name = "DevEnCoursUtilisation")
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




}
