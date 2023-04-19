package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "VEH_BY_DEV")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veh_By_Dev {
    @Id
    @Column(name = "GRPMOD")
    private Integer grpmod;
    @ManyToOne
    @JoinColumn(name = "GRPMOD", referencedColumnName = "CODE_VEH", insertable = false, updatable = false)
    private Vehid vehid;

    @ManyToOne
    @JoinColumn(name = "IdDev", referencedColumnName = "IdDev", insertable = false, updatable = false)
    private DEV dev;
}
