package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SITES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SITES {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSite")
    private Long idSite;

    @Column(name = "NomSite")
    private String nomSite;
}
