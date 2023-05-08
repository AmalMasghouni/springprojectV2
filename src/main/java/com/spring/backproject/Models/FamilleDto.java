package com.spring.backproject.Models;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Data
public class FamilleDto {
    @Column(name = "Nomfamille")
    private String nomFamille;

    @Column(name = "Description")
    private String description;

    @Column(name = "Phrase")
    private String phrase;

    @Column(name = "DescrAnglais")
    private String descrAnglais;

    @Column(name = "Ordre")
    private Short ordre ;

    @Column(name = "UPDATE")
    private Date update;

    @Column(name = "GuidedMethFilter")
    private Boolean guidedMethFilter;

    @Column(name = "SparePartsFilter")
    private Boolean sparePartsFilter;
}
