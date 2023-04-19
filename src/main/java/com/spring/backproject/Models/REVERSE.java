package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REVERSE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class REVERSE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReverse")
    private Integer idReverse;

    @Column(name = "NomReverse", length = 50)
    private String nomReverse;

    @Column(name = "NumDico")
    private Integer numDico;

    @Column(name = "PROFIL", length = 30)
    private String profil;

    @Column(name = "GEO", length = 30)
    private String geo;
}
