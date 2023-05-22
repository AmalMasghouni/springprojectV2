package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MENU")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMenu")
    private Long id;

    @Column(name = "PathMenu", length = 255)
    private String pathMenu;

    @Column(name = "DateDemandeXLS")
    private Timestamp dateDemandeXLS;

    @Column(name = "HeureDemandeXLS")
    private Timestamp heureDemandeXLS;

    @Column(name = "LectDef", columnDefinition = "boolean default false")
    private Boolean lectDef;

    @Column(name = "EffDef", columnDefinition = "boolean default false")
    private Boolean effDef;

    @Column(name = "Genere", columnDefinition = "boolean default false")
    private Boolean genere;

    @Column(name = "MessageInit")
    private Boolean messageInit;

    @Column(name = "CodeMessageInit", length = 80)
    private String codeMessageInit;

    @Column(name = "MessagePrinc")
    private Boolean messagePrinc;

    @Column(name = "CodeMessagePrinc", length = 80)
    private String codeMessagePrinc;

    @Column(name = "NoDtcNegFilter", columnDefinition = "boolean default false")
    private Boolean noDtcNegFilter;
}
