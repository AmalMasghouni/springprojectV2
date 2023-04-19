package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "VERSION_MAJ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VERSION_MAJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVer")
    private  Long idVer;
    @Column(name="NomVer")
    private String nomVer;
    @Column(name = "Ordre")
    private short ordre;
    @Column(name = "IdMaj")
    private Integer idMaj;
    @Column(name="compilateur")
    private String compilateur;
    @Column(name="MainUpdateVer")
    private String mainUpdateVer;


}
