package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VersionMaj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVersion;
    private String nomVersion;
    private String versionAtal;
    private String type;
    private Integer ordre;
    private String etat;
    @OneToMany(fetch = FetchType.LAZY)
    List<MAJ> MAJList;
}
