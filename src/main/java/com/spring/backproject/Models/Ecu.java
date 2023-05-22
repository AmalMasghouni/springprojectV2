package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ECU")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ecu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEcu")
    private Integer idEcu;

    @Column(name = "NomEcu")
    private String nomEcu;



    @Column(name = "TypeInj")
    private String typeInj;

    @Column(name = "PhraseTypeInj")
    private String phraseTypeInj;

    @Column(name = "UPDATE")
    private Date update;

    @Column(name = "NomEcuGPC")
    private String nomEcuGPC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdFamille", referencedColumnName = "IdFamille", insertable = false, updatable = false)
    private Famille famille;

}
