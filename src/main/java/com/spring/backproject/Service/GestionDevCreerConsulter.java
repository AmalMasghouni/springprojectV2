package com.spring.backproject.Service;


import com.spring.backproject.Models.CreerConsulter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionDevCreerConsulter {
    private final JdbcTemplate jdbcTemplate;

    public GestionDevCreerConsulter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
public List<CreerConsulter> getGestionDevCreerConsulter(){
    String sql =
            "SELECT d.id_dev AS n, d.nom_dev, d.etat_dev, c.refCDC, c.nomCDC, d.nomDll, f.nomFamille, e.nom_ecu, CONCAT(u.first_name, ' ', u.last_name) AS developpeur "+
                    "FROM DEV d " +
                    "JOIN CDC c ON d.idcdc = c.idCDC "+
                    "JOIN ECU e ON d.id_ecu = e.id_ecu "+
                    "JOIN Utilisateur u ON d.idrc= u.id "+
                    "JOIN Famille f ON e.id_famille = f.id_famille " ;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        CreerConsulter tableData = new CreerConsulter();
        tableData.setN(rs.getString("n"));
        tableData.setNom(rs.getString("nom_dev"));

        tableData.setEtat(rs.getString("etat_dev"));
        tableData.setCDC(rs.getString("refCDC"));
        tableData.setDLL(rs.getString("nomDll"));
        tableData.setFamille(rs.getString("nomFamille"));
        tableData.setECU(rs.getString("nom_ecu"));
        tableData.setDeveloppeur(rs.getString("developpeur"));

        return tableData;
});

}


}

