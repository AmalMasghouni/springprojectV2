package com.spring.backproject.Controller;

import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.*;
import com.spring.backproject.Service.GestionDevCreerConsulter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

@CrossOrigin(origins = "http://localhost:4200")
public class DashbordController {
    @Autowired
    GestionDevCreerConsulter gestionDevCreerConsulter;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @GetMapping("/filtrer")
    public ResponseEntity<?> filter(@RequestBody Map<String, String> filters) {
        String cdc = filters.get("cdc");
        String dev=filters.get("dev");
        String version=filters.get("version");
        String marque= filters.get("marque");
        String modele=filters.get("modele");
        String site=filters.get("site");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cdc", cdc);
        parameterSource.addValue("dev", dev);
        parameterSource.addValue("version", version);
        parameterSource.addValue("marque", marque);
        parameterSource.addValue("modele", modele);
        parameterSource.addValue("site", site);


        String sql =
                "SELECT d.id_dev AS n, d.nom_dev, d.etat_dev, c.refCDC, c.nomCDC, d.nomDll, f.nomFamille, e.nom_ecu, CONCAT(u.first_name, ' ', u.last_name) AS developpeur "+
                        "FROM DEV d , VERSIONMAJ V , MARQUE m , Vehid v" +
                        "JOIN CDC c ON d.idcdc = c.idCDC "+
                        "JOIN ECU e ON d.id_ecu = e.id_ecu "+
                        "JOIN Utilisateur u ON d.idrc= u.id "+
                        "JOIN Famille f ON e.id_famille = f.id_famille "+
                        "JOIN SITES s ON s.NomSite = u.sitesList "+
                         "JOIN VersionMaj ver ON ver";
        if(StringUtils.isNotEmpty(cdc)) {
            sql += " WHERE d.idcdc = :cdc " ;
        }

        if(StringUtils.isNotEmpty(version)) {
            String nomMaj = version.substring(0, version.indexOf("("));
            String typeMaj = version.substring(version.indexOf("(") + 1, version.indexOf(")"));
            System.out.println("NOM MAJ +++ : "+nomMaj);
            sql += " AND V.NomVer = :nomMaj ";

        }
        if(StringUtils.isNotEmpty(marque)) {
            sql += " AND m.nommar = :marque ";
        }
        if(StringUtils.isNotEmpty(modele)) {
            sql += " AND ver.nomVeh = :modele ";
        }
        /*if(StringUtils.isNotEmpty(site)) {
            sql += " WHERE d.idSite = (" + selSite + ")";
        }*/


      /* List<CreerConsulter> results = namedParameterJdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> {
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

       });*/


        return ResponseEntity.ok().build();
    }
















 /* @GetMapping("/getTabCreerConsulter")
    public List<CreerConsulter> getTableData() {return gestionDevCreerConsulter.getGestionDevCreerConsulter();}
    @GetMapping("/getdev")
    public List<String> getAllNomDev() {
        return userRepository.findAllUtilisateur();
    }
    @GetMapping("/getrefcdc")
    public List<String> getAllRefCDC() {
        return cdcreposit.findAllRefCDC();
    }
    @GetMapping("/getAllNomSite")
    public List<String> getAllNomSite(){return siteRep.findAllNomSite(); }
    @GetMapping("/getAllVersion")
    public List<String> getAllVersion(){return versionRep.findAllVersion(); }
    @GetMapping("/getAllMarque")
    public List<String> getAllMarque(){return marqueRep.findAllMarque() ; }
    @GetMapping("/getAllModele")
    public List<String> getAllModele(){return modeleRepository.findAllNomModele() ; }
@GetMapping("/getModeleParMarque")
    public List<String> getModeleParMarque(@Param("nommar") String nomMarque){return modeleRepository.findModelesByMarque();}
@GetMapping("/getallVoiture")
public List<Vehid> getallvoiture(){
      return vehiculeRepository.findAll();
}*/


    // /vrhiculr/zjout

  /*  @PutMapping("/update-vehicule")
    public ResponseEntity<?> updateVehicule(@RequestBody Vehid vehicule) {
        Vehid updatedVehicule = marqueRep.save(vehicule);
        return ResponseEntity.ok(updatedVehicule);
    }*/


/*@GetMapping("/getAllFamille")
public List<Famille> getAllFamille(){
        return familleRep.findAll();}*/
    /*@PostMapping("/ajout-famille")
    public ResponseEntity<?> AjoutFamille(@RequestBody  FamilleDto familleDto){
        Famille famille=new Famille();
        famille.setNomFamille(familleDto.getNomFamille());
        famille.setDescription(famille.getDescription());
        famille.setPhrase(familleDto.getPhrase());
        famille.setDescrAnglais(famille.getDescrAnglais());
        famille.setOrdre(familleDto.getOrdre());
        famille.setUpdate(familleDto.getUpdate());
        famille.setGuidedMethFilter(familleDto.getGuidedMethFilter());
        famille.setSparePartsFilter(familleDto.getSparePartsFilter());
        familleRep.save(famille);


        return ResponseEntity.ok().body(new AuthResponse(true,"famille cree"));
    }*/

    @GetMapping("/filtrer")
    public ResponseEntity<?> filtrer(
            @RequestParam(required = false) String modele,
            @RequestParam(required = false) String marque,
            @RequestParam(required = false) String version,
            @RequestParam(required = false) String site,
            @RequestParam(required = false) String utilisateur,
            @RequestParam(required = false) String cdc) {

        // Effectuer le traitement en fonction des paramètres reçus

        return ResponseEntity.ok().build();
    }

}