package com.spring.backproject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CDC")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CDC implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCDC")
    private Long idCDC;

    @Column(name = "NomCDC", length = 100)
    private String nomCDC;

    @Column(name = "RefCDC", length = 50)
    private String refCDC;

    @Column(name = "IndCDC", length = 50)
    private String indCDC;

 @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdReverse")
    private Reverse reverse;

    @Column(name = "IdSite")
    private Integer idSite;

    @Column(name = "Creation")
    private Timestamp creation;

    @Column(name = "SignatureOk")
    private Boolean signatureOk;

    @Column(name = "RefCDCArdia", length = 10)
    private String refCDCArdia;
    @JsonIgnore
    @OneToMany(mappedBy = "cdc", fetch = FetchType.LAZY)
    private List<DEV> DEVS;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Fonction> fonctionList=new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurList;


}
