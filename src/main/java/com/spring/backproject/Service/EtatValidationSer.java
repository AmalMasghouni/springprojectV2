package com.spring.backproject.Service;

import com.spring.backproject.Models.CreerConsulter;
import com.spring.backproject.Models.EtatValidationDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtatValidationSer {
    private final JdbcTemplate jdbcTemplate;

    public EtatValidationSer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /*public List<EtatValidationDto> getEtatValidation(){
        String sql =
                "SELECT d.id_dev AS n, d.nom_dev, d.etat_dev, c.refCDC, c.nomCDC, d.nomDll, f.nomFamille, e.nom_ecu, CONCAT(u.first_name, ' ', u.last_name) AS developpeur "+
                        "FROM DEV d " +
                        "JOIN CDC c ON d.idcdc = c.idCDC "+
                        "JOIN ECU e ON d.id_ecu = e.id_ecu "+
                        "JOIN Utilisateur u ON d.idrc= u.id "+
                        "JOIN Famille f ON e.id_famille = f.id_famille " ;
        return jdbcTemplate.query(sql, (rs, rowNum)-> {
           EtatValidationDto tableData = new EtatValidationDto();
            tableData.setN(rs.getString("n"));
            tableData.setNomDev(rs.getString("nom_dev"));
            tableData.setNomCDC(rs.getString("nomcdc"));
            tableData.setRefCDC(rs.getString("refcdc"));
            tableData.setSite(rs.getString("site"));
            tableData.setDeveloppeur(rs.getString("developpeur"));
            tableData.setTestintegration(rs.getString("testintegration"));
            tableData.setTestunitaire(rs.getString("testunitaire"));
            tableData.setEtat(rs.getString("etat"));
            return tableData;
        });
    }*/
}
