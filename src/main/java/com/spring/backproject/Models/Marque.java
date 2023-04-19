package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Marque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODMAR")
    private Integer codmar;

    @Column(name = "NOMMAR")
    private String nommar;

    @Column(name = "Profil")
    private String profil;

    @Column(name = "Geo")
    private String geo;

    @Column(name = "Cible")
    private Integer cible;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cible", referencedColumnName = "IdMaj", insertable = false, updatable = false)
    private MAJ maj;*/
}
