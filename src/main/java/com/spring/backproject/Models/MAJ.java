package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MAJ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MAJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMaj")
    private Long idMaj;

    @Column(name = "NomMaj")
    private String nomMaj;

    @Column(name = "TypeMaj")
    private String typeMaj;

    @Column(name = "StatusMaj")
    private String statusMaj;

    @Column(name = "Ordre")
    private Short ordre;

    @Column(name = "EnCours")
    private Boolean enCours;

    @Column(name = "AtalMaj")
    private String atalMaj;

    @Column(name = "NomMajOffice")
    private String nomMajOffice;
}
