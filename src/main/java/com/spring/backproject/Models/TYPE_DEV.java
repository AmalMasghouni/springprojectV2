package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TYPE_DEV")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TYPE_DEV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTypeDev")
    private Integer idTypeDev;

    @Column(name = "NomTypeDev", length = 50)
    private String nomTypeDev;

    @Column(name = "NumDico")
    private Short numDico;

    @Column(name = "Ordre")
    private Integer ordre;
}
