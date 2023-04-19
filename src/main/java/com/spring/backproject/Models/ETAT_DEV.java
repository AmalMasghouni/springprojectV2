package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ETAT_DEV")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ETAT_DEV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEtatDev")
    private Integer idEtatDev;

    @Column(name = "NomEtatDev", length = 50)
    private String nomEtatDev;

    @Column(name = "NumDico")
    private Integer numDico;

    @Column(name = "Ordre")
    private Short ordre;
}
