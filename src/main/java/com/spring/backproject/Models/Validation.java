package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VALIDATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdValid")
    private Integer idValid;

    @Column(name = "TypeValid", length = 255)
    private String typeValid;

    @Column(name = "DateValid")
    private String DateValid;

    @Column(name = "EtatValid")
    private String EtatValid;
    @Column(name = "IdVer")
    private String idVer;
    @ManyToMany(mappedBy = "validationList",fetch = FetchType.LAZY)
    private List<DEV> DEVList =new ArrayList<>();
}
