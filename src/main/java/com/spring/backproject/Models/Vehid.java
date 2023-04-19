package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Vehid")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_VEH")
    private Integer codeVeh;

    @Column(name = "NOMVEH")
    private String nomVeh;

    @Column(name = "NOMINTERNE")
    private String nomInterne;

    @Column(name = "TESTGLOBAL")
    private Boolean testGlobal ;

    @Column(name = "MSG_DIAG", length = 350)
    private String msgDiag;

    @Column(name = "GRPMARQ")
    private String grpMarq;

    @Column(name = "FRO")
    private String fro;

    @Column(name = "T")
    private String t;


    @Column(name = "UPD")
    private Date upd;

    @Column(name = "Remarque", length = 50)
    private String remarque;

    @Column(name = "OnlyElec")
    private Boolean onlyElec;

    @Column(name = "AVERTISS_ELEC")
    private Boolean avertissElec ;

    @Column(name = "AVERTISS_HYBRID")
    private Boolean avertissHybrid ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMarque")
    private Marque marque;
}
