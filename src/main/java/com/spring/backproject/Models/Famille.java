package com.spring.backproject.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Short ordre;

    @Column(name = "UPDATE")
    private Date update;

    @Column(name = "GuidedMethFilter")
    private Boolean guidedMethFilter;

    @Column(name = "SparePartsFilter")
    private Boolean sparePartsFilter;
    @Column(name="IdfamilleHaynes")
    private Integer idFamilleHynes;
    @Column(name="DescriptionHaynes")
    private String descriptionHaynes;


}
