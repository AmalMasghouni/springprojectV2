package com.spring.backproject.Controller;

import com.zaxxer.hikari.pool.HikariProxyResultSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class FiltrageController {
  @GetMapping("/filtrageDev")
    public ResponseEntity<?> filterDev( @RequestParam(required = false) String grpMarque,@RequestParam (required = false) String modele,
                                             @RequestParam(required = false) String vers,
                                             @RequestParam(required = false) String nomSite,
                                             @RequestParam(required = false) String utilisateur,
                                             @RequestParam (required = false)String refCdc)

  {  String sql="SELECT d.id_dev as n, d.nom_dev as nomDev, c.nomCDC as cdc, c.idCDC as idcdc, c.refCDC as refCDCActia,\n" +
          "       c.creation as datacdc, c.indCDC as indice," +
          "       d.nomDLL as nomDll," +
          "       CONCAT(u.first_name, ' ', u.last_name) as utilisateur," +
          "       maj.nom_maj as maj," +
          "       c.refCDC as refCdc," +
          "       veh.nomveh as nomVehicule,veh.grpmarq as marque ,veh.nominterne as nomInterne" +
          "FROM DEV d" +
          "JOIN CDC c ON d.idcdc = c.idCDC" +
          "JOIN Utilisateur u ON d.idrc= u.id " +
          "JOIN maj maj on maj.id_maj = d.id_maj" +
          "JOIN dev_vehicule devehi on devehi.id_dev = d.id_dev" +
          "Join vehid veh on veh.code_veh = devehi.code_veh ";



     /*   String sql =
                "SELECT d.id_dev as n, d.nom_dev as nomDev, c.nomCDC as cdc, c.idCDC as idcdc, c.refCDC as refCDCActia,\n" +
                        "       c.creation as datacdc, c.indCDC as indice,\n" +
                        "       d.nomDLL as nomDll,\n" +
                        "       CONCAT(u.first_name, ' ', u.last_name) as utilisateur,\n" +
                        "       maj.nom_maj as maj,\n" +
                        "       c.refCDC as refCdc,\n" +
                        "       veh.nomveh as nomVehicule, veh.grpmarq as marque, veh.nominterne as nomInterne " +
                        "FROM DEV d " +
                        "JOIN CDC c ON d.idcdc = c.idCDC " +
                        "JOIN Utilisateur u ON d.idrc = u.id " +
                        "JOIN maj maj ON maj.id_maj = d.id_maj " +
                        "JOIN dev_vehicule devehi ON devehi.id_dev = d.id_dev " +
                        "JOIN vehid veh ON veh.code_veh = devehi.code_veh ";*/

        boolean whereClauseAdded = false;

        if (StringUtils.isNotEmpty(refCdc)) {
            sql += " WHERE c.refCDC = :refCdc ";
            whereClauseAdded = true;
        }

        if (StringUtils.isNotEmpty(vers)) {
            String nomMaj = vers.substring(0, vers.indexOf("("));
            String typeMaj = vers.substring(vers.indexOf("(") + 1, vers.indexOf(")"));

            if (whereClauseAdded) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                whereClauseAdded = true;
            }

            sql += " maj.nom_maj = :nomMaj AND maj.type_maj = :typeMaj ";
        }

        if (StringUtils.isNotEmpty(grpMarque)) {
            if (whereClauseAdded) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                whereClauseAdded = true;
            }

            sql += " m.nommar = :grpMarque ";
        }

        if (StringUtils.isNotEmpty(modele)) {
            if (whereClauseAdded) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                whereClauseAdded = true;
            }

            sql += " ver.nomVeh = :modele ";
        }

        if (StringUtils.isNotEmpty(nomSite)) {
            if (whereClauseAdded) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
            }

            sql += " d.idSite = :site ";
        }
        return ResponseEntity.ok(sql);
    }
}
