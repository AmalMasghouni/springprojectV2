package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Famille")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Famille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFamille")
    private Long idFamille;

    @Column(name = "Nomfamille")
    private String nomFamille;

    @Column(name = "Description")
    private String description;

    @Column(name = "Phrase")
    private String phrase;

    @Column(name = "DescrAnglais")
    private String descrAnglais;

    @Column(name = "Ordre")
    private Short ordre = 0;

    @Column(name = "UPDATE")
    private String update;

    @Column(name = "GuidedMethFilter")
    private Boolean guidedMethFilter;

    @Column(name = "SparePartsFilter")
    private Boolean sparePartsFilter;


}
