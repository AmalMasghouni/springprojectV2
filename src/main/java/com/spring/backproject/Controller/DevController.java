package com.spring.backproject.Controller;

import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.*;
import com.spring.backproject.Service.GestionDeveloppementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class DevController {
    @Autowired
    DevRepository devrepository;
    @Autowired
    SiteRep siteRep;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    MajRepository majRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CdcRep cdcRep;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GestionDeveloppementService gestionDeveloppementService;

    public DevController(GestionDeveloppementService gestionDeveloppementService) {
        this.gestionDeveloppementService = gestionDeveloppementService;
    }

    @GetMapping("/getDevById/{idDev}")
    public ResponseEntity<?> getDevById(@PathVariable Long idDev){
      List<DEV> DEVList = devrepository.findByIdDev(idDev);
      List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(DEVList);
        return ResponseEntity.ok(devMapList);
    }
    @GetMapping("/getAllDev")
    public ResponseEntity<?> getAllDev(){
        List<DEV> DEVList = devrepository.findAll();
        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(DEVList);
        return ResponseEntity.ok(devMapList);
    }
    @GetMapping("/codeVehicule/{grpMarque}/{nomVe}/{nomInterne}/{nomMaj}/{typeMaj}/{nomSite}/{nom}/{prenom}" +
            "/{refCdc}")
    public ResponseEntity<?> getCodeVehByGrpMarque(
            @PathVariable String grpMarque,@PathVariable String modele,
            @PathVariable String vers,
            @PathVariable String nomSite,
            @PathVariable String utilisateur,
            @PathVariable String refCdc)

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
        String nomVeh = null;
        String nomInterne = null;

        if (modele != null && modele.matches("^\\w+\\[\\w+\\]$")) {
            String[] parts = modele.split("\\[");
            nomVeh = parts[0];
            nomInterne = parts[1].substring(0, parts[1].length() - 1);
        }
        /*    Recherche id vehicule selon les 3parametre grpMar nomVeh NomInterne */
        List<Vehid> vehicules = vehiculeRepository.findAllCodeVehByNomVehAndNomInterneAndGrpMarq(nomVeh,nomInterne,grpMarque);
        List<Integer> codesVehicules = new ArrayList<>();
        for (Vehid v : vehicules) {codesVehicules.add(v.getCodeVeh());}


        /*        Recherche IdVersion selon les deux parametre nomMaj et TypeMaj*/

        String regex = "^(.*?)\\((.*?)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(vers);
        if (matcher.matches()) {String nomMaj = matcher.group(1);
            String typeMaj = matcher.group(2);
        List<MAJ> version =majRepository.findAllIdMajByNomMajAndTypeMaj(nomMaj,typeMaj);
        List<Long> codeMaj = new ArrayList<>();
        for (MAJ v : version) {codeMaj.add(v.getIdMaj());}


        /*          Recherche IdSite selon NomSite*/
        List<SITES> sites =siteRep.findAllIdSiteByNomSite(nomSite);
        List<Integer> codesite = new ArrayList<>();
        for (SITES v : sites) {codesite.add(v.getIdSite());}
        // Recherche  idDeveloppeur selon nom et prenom developpeur
            String nom = null;
            String prenom = null;

            if (utilisateur != null) {
                String[] parts = utilisateur.split(" ");
                if (parts.length >= 2) {
                    nom = parts[0];
                    prenom = parts[1];
                }
            }
        List<Utilisateur> utilisateurs=userRepository.findAllIdByFirstNameAndLastName(nom,prenom);
        List<Long> codeuser = new ArrayList<>();
        for (Utilisateur v : utilisateurs) {codeuser.add(v.getId());}
        // Recherche idCDC selon REF CDC
        List<CDC> cdcs=cdcRep.findAllIdCDCByRefCDC(refCdc);
        List<Long> codeCdc= new ArrayList<>();
        for (CDC v : cdcs) {codeCdc.add(v.getIdCDC());}

Map<String,List> response=new HashMap<>();
        response.put("vehicule",codesVehicules);
        response.put("version",codeMaj);
        response.put("site",codesite);
        response.put("user",codeuser);
        response.put("cdc",codeCdc);
//List<DEV> devs =devrepository.findAllByUtilisateurIdOrIdSiteOrCdcIdCDCOrMajIdMajOrVehidListCodeVeh()

        if (!codesVehicules.isEmpty() || !codeMaj.isEmpty()|| !codesite.isEmpty()|| !codeuser.isEmpty()|| !codeCdc.isEmpty()) {

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        return null;
    }

    @PutMapping("/modifier-dev/{n}")
    public ResponseEntity<?> modifierDev(@PathVariable Long idDev){
        List<DEV> DEVList = devrepository.findByIdDev(idDev);

        return ResponseEntity.ok(null);
    }
    @GetMapping("/cherhcherparDll/{nomDll}")
    public ResponseEntity<?> chercherparDll(@PathVariable("nomDll") String nomDll){
        List<DEV> Listdev = devrepository.findByNomDll(nomDll);
        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
        return ResponseEntity.ok(devMapList);

    }
    @GetMapping("/rechercheDEVparVersion")
    public ResponseEntity<?> rechercheDEvVersion (@RequestParam("version") String version){
        String regex = "^(.*?)\\((.*?)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(version);
        if (matcher.matches()) {String nomMaj = matcher.group(1);
            String typeMaj = matcher.group(2);
            List<DEV> Listdev=devrepository.findIdDevByMajNomMajAndMajTypeMaj(nomMaj,typeMaj);
            List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
            return ResponseEntity.ok(devMapList);}
        else {
            return ResponseEntity.badRequest().body("Format de données invalide.");}

    }
   /* @GetMapping("/rechercheDEVparNomsite")
    public ResponseEntity<?> rechercheDEvNomDev (@RequestParam("site") String site){
        SITES sites = siteRep.findByNomSite(site);
        Integer idS=sites.getIdSite();
            List<DEV> Listdev=devrepository.findByIdSite(idS);
            List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
            return ResponseEntity.ok(devMapList);}*/
   /*@GetMapping("/rechercheDEVparutilisateur")
    public ResponseEntity<?> rechercheDevUtilisateur (@RequestParam("utilisateur") String utilisateur){
        String nom = null;
        String prenom = null;

        if (utilisateur != null) {
            String[] parts = utilisateur.split(" ");
            if (parts.length >= 2) {
                nom = parts[0];
                prenom = parts[1];
            }
        }
        List<DEV> Listdev=devrepository.findByUtilisateurFirstNameAndUtilisateurLastName(nom,prenom);
        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
        return ResponseEntity.ok(devMapList);}*/

    /*@GetMapping("/rechercheDEVparrefCDC")
    public ResponseEntity<?> rechercheDEvefCDC (@RequestParam("refCDC") String refCDC){
        List<DEV> Listdev=devrepository.findByCdcRefCDC(refCDC);
        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
        return ResponseEntity.ok(devMapList);}*/
   /* @GetMapping("/rechercheDEVparMarque")
    public ResponseEntity<?> rechercheDEvMarque(@RequestParam("marque") String marque){
        List<DEV> Listdev=devrepository.findByVehidListGrpMarq(marque);
        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
        return ResponseEntity.ok(devMapList);}*/
    /*@GetMapping("/rechercheDEVparmodele")
    public ResponseEntity<?> rechercheDEvModele(@RequestParam("modele") String modele){
        String nomVeh = null;
        String nomInterne = null;

        if (modele != null && modele.matches("^\\w+\\[\\w+\\]$")) {
            String[] parts = modele.split("\\[");
            nomVeh = parts[0];
            nomInterne = parts[1].substring(0, parts[1].length() - 1);

            List<DEV> Listdev=devrepository.findByVehidListNomVehAndVehidListNomInterne(nomVeh,nomInterne);
            List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(Listdev);
            return ResponseEntity.ok(devMapList);}
        else {
            return ResponseEntity.badRequest().body("Format de données invalide.");}}*/


    /*@GetMapping("/filterDev")
    public ResponseEntity<?> filterDev(@RequestParam(value = "version", required = false) String version,
                                       @RequestParam(value = "site", required = false) String site,
                                       @RequestParam(value = "utilisateur", required = false) String utilisateur,
                                       @RequestParam(value = "refCDC", required = false) String refCDC,
                                       @RequestParam(value = "marque", required = false) String marque,
                                       @RequestParam(value = "modele", required = false) String modele) {
        List<DEV> filteredDevs = new ArrayList<>();

        if (version != null) {
            String regex = "^(.*?)\\((.*?)\\)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(version);
            if (matcher.matches()) {String nomMaj = matcher.group(1);
                String typeMaj = matcher.group(2);
                List<DEV> Listdev=devrepository.findIdDevByMajNomMajAndMajTypeMaj(nomMaj,typeMaj);}
        }

        if (site != null) {
            SITES sites = siteRep.findByNomSite(site);
            Integer idS=sites.getIdSite();
            List<DEV> Listdev=devrepository.findByIdSite(idS);
        }

        if (utilisateur != null) {
            String nom = null;
            String prenom = null;

            if (utilisateur != null) {
                String[] parts = utilisateur.split(" ");
                if (parts.length >= 2) {
                    nom = parts[0];
                    prenom = parts[1];
                }
            }
            List<DEV> Listdev=devrepository.findByUtilisateurFirstNameAndUtilisateurLastName(nom,prenom);
        }

        if (refCDC != null) {
            List<DEV> Listdev=devrepository.findByCdcRefCDC(refCDC);
        }

        if (marque != null) {
            List<DEV> Listdev=devrepository.findByVehidListGrpMarq(marque);
        }

        if (modele != null) {
            String nomVeh = null;
            String nomInterne = null;

            if (modele != null && modele.matches("^\\w+\\[\\w+\\]$")) {
                String[] parts = modele.split("\\[");
                nomVeh = parts[0];
                nomInterne = parts[1].substring(0, parts[1].length() - 1);

                List<DEV> Listdev=devrepository.findIdDevByMajNomMajAndMajTypeMaj(nomVeh,nomInterne);}
        }

        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(filteredDevs);
        return ResponseEntity.ok(devMapList);
    }



*/
    @GetMapping("/filterDev")
    public ResponseEntity<?> filterDev(@RequestParam(value = "version", required = false) String version,
                                       @RequestParam(value = "site", required = false) String site,
                                       @RequestParam(value = "utilisateur", required = false) String utilisateur,
                                       @RequestParam(value = "refCDC", required = false) String refCDC,
                                       @RequestParam(value = "marque", required = false) String marque,
                                       @RequestParam(value = "nomVeh", required = false) String nomVeh,
                                       @RequestParam(value = "nomInterne", required = false) String nomInterne) {
        List<DEV> filteredDevs = new ArrayList<>();
        Long id=null;
        if (version != null) {
            String regex = "^(.*?)\\((.*?)\\)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(version);
            if (matcher.matches()) {
                String nomMaj = matcher.group(1);
                String typeMaj = matcher.group(2);
                DEV devs=devrepository.findByMajNomMajAndMajTypeMaj(nomMaj, typeMaj);
                Long devversion=devs.getIdDev();


            }
        }
        Integer s=0;
        if (site != null) {
            SITES sites = siteRep.findByNomSite(site);
            Integer idS = sites.getIdSite();
           DEV devs=devrepository.findByIdSite(idS);
           Long devi=devs.getIdDev();
           s=s+1;
        }

        if (utilisateur != null) {
            String nom = null;
            String prenom = null;

            if (utilisateur != null) {
                String[] parts = utilisateur.split(" ");
                if (parts.length >= 2) {
                    nom = parts[0];
                    prenom = parts[1];
                }
            }
            DEV devut=devrepository.findByUtilisateurFirstNameAndUtilisateurLastName(nom, prenom);
            Long dev=devut.getIdDev();
            s=s+1;

        }

        if (refCDC != null) {
            DEV decdc=devrepository.findByCdcRefCDC(refCDC);
            Long dev=decdc.getIdDev();
            s=s+1;
        }

        if (marque != null) {
            DEV devmarque=devrepository.findByVehidListGrpMarq(marque);
            Long devidma=devmarque.getIdDev();
            s=s+1;
        }

        if (nomVeh != null&& nomInterne!=null) {
DEV modele=devrepository.findByVehidListNomVehAndVehidListNomInterne(nomVeh,nomInterne);
                Long devmodel=modele.getIdDev();
                s=s+1;

            }


        List<Map<String, Object>> devMapList = gestionDeveloppementService.createDevMapList(filteredDevs);
        return ResponseEntity.ok(devMapList);
    }


}


