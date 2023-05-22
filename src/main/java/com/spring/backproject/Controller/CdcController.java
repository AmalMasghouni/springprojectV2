package com.spring.backproject.Controller;

import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.SiteRep;
import com.spring.backproject.Repository.CdcRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class CdcController {
    @Autowired
    CdcRep cdcRespositroy;
    @Autowired
    SiteRep siteRep;
    @PostMapping("/ajout-cdc")
    public ResponseEntity<?> AjoutCdc(@RequestBody CdcDto cdcDto){
        CDC cdc=new CDC();
        SITES sites =siteRep.findByNomSite(cdcDto.getIdSite());

        Integer codesite=sites.getIdSite();
        cdc.setNomCDC(cdcDto.getNomCDC());
        cdc.setRefCDC(cdcDto.getRefCDC());
        cdc.setIdSite(codesite);
        cdc.setRefCDCArdia(cdcDto.getRefCDCArdia());
        cdc.setIndCDC(cdcDto.getIndCDC());
        cdc.setCreation(cdcDto.getCreation());
        cdc.setSignatureOk(cdcDto.getSignatureOk());
        CDC savedcdc= cdcRespositroy.save(cdc);
       return ResponseEntity.ok(savedcdc);
    }

    @DeleteMapping("/delete-cdc/{idCDC}")
    public ResponseEntity<?> DeletCdc(@PathVariable Long idCDC){
        cdcRespositroy.deleteById(idCDC);
        return ResponseEntity.ok(new AuthResponse(true,"Cdc supprime"));
    }
    @GetMapping("/getAllCdc")
    public ResponseEntity<?>getAllCdc(){
        List<CDC> cdcs=cdcRespositroy.findAll();
        return ResponseEntity.ok(cdcs);}
    @GetMapping("/rechercheparRefCdc/{refCDC}")
    public ResponseEntity<?> rechercheparRefCdc (@PathVariable("refCDC") String refCDC) {
        List<CDC> cdcs=cdcRespositroy.findByRefCDC(refCDC);
        List<Map<String, Object>> cdcMap = cdcs.stream()
                .map(cdc -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idCDC",cdc.getIdCDC());
                    map.put("nomCDC", cdc.getNomCDC());
                    map.put("refCDC", cdc.getRefCDC());
                    map.put("indCDC", cdc.getIndCDC());
                    if (cdc.getCreation() != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String creationDate = dateFormat.format(cdc.getCreation());
                        map.put("creation", creationDate);
                    } else {
                        map.put("creation", "");
                    }

                    if (cdc.getSignatureOk() != null && cdc.getSignatureOk()) {
                        map.put("signatureOk", "signé");
                    } else {
                        map.put("signatureOk", "non signé");
                    }

                    map.put("refCDCArdia", cdc.getRefCDCArdia());
                    if (cdc.getIdSite() != null) {

                        SITES sites =siteRep.findByIdSite(cdc.getIdCDC().intValue());

                        if (sites != null) {
                            map.put("site", sites.getNomSite());
                        } else {
                            map.put("site", "");
                        }
                    } else {
                        map.put("site", "");}

                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(cdcMap);
    }
@GetMapping("/rechercheCDCparVersion")
public ResponseEntity<?> rechercheCDCparVersion (@RequestParam("version") String version){
    String regex = "^(.*?)\\((.*?)\\)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(version);
    if (matcher.matches()) {String nomMaj = matcher.group(1);
        String typeMaj = matcher.group(2);
        List<CDC> cdcs=cdcRespositroy.findAllIdCDCByDEVSMajNomMajAndDEVSMajTypeMaj(nomMaj,typeMaj);
        List<Map<String, Object>> cdcMap = cdcs.stream()
                .map(cdc -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idCDC",cdc.getIdCDC());
                    map.put("nomCDC", cdc.getNomCDC());
                    map.put("refCDC", cdc.getRefCDC());
                    map.put("indCDC", cdc.getIndCDC());
                    if (cdc.getCreation() != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String creationDate = dateFormat.format(cdc.getCreation());
                        map.put("creation", creationDate);
                    } else {
                        map.put("creation", "");
                    }

                    if (cdc.getSignatureOk() != null && cdc.getSignatureOk()) {
                        map.put("signatureOk", "signé");
                    } else {
                        map.put("signatureOk", "non signé");
                    }

                    map.put("refCDCArdia", cdc.getRefCDCArdia());
                    if (cdc.getIdSite() != null) {

                        SITES sites =siteRep.findByIdSite(cdc.getIdCDC().intValue());

                        if (sites != null) {
                            map.put("site", sites.getNomSite());
                        } else {
                            map.put("site", "");
                        }
                    } else {
                        map.put("site", "");}

                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(cdcMap);

    } else {
        return ResponseEntity.badRequest().body("Format de données invalide.");
    }

}
    @GetMapping("/getAllCdcSiteById/{idCDC}")
    public ResponseEntity<List<Map<String, Object>>> getAllCdcSiteById(@PathVariable Long idCDC) {
        Optional<CDC> cdcs = cdcRespositroy.findById(idCDC);
        List<Map<String, Object>> cdcMap = cdcs.stream()
                .map(cdc -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idCDC",cdc.getIdCDC());
                    map.put("nomCDC", cdc.getNomCDC());
                    map.put("refCDC", cdc.getRefCDC());
                    map.put("indCDC", cdc.getIndCDC());
                    if (cdc.getCreation() != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String creationDate = dateFormat.format(cdc.getCreation());
                        map.put("creation", creationDate);
                    } else {
                        map.put("creation", "");
                    }
                    if (cdc.getSignatureOk() != null && cdc.getSignatureOk()) {
                        map.put("signatureOk", "signé");
                    } else {
                        map.put("signatureOk", "non signé");
                    }
                    map.put("refCDCArdia", cdc.getRefCDCArdia());
                    if (cdc.getIdSite() != null) {
                        SITES sites =siteRep.findByIdSite(cdc.getIdSite());
                        if (sites != null) {
                            map.put("idSite", sites.getNomSite());
                        } else {
                            map.put("idSite", "");
                        }
                    } else {
                        map.put("idSite", "");}

                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(cdcMap);
    }
    @PutMapping("/modifier-cdc/{idCDC}")
    public ResponseEntity<?> modifierCdc(@PathVariable Long idCDC, @RequestBody CdcModifDto cdcDto) {
        CDC cdc = cdcRespositroy.findById(idCDC).orElse(null);

        if (cdc == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            SITES sites = siteRep.findByNomSite(cdcDto.getIdSite());
            Integer idSite = sites.getIdSite();

            cdc.setNomCDC(cdcDto.getNomCDC());
            cdc.setRefCDC(cdcDto.getRefCDC());
            cdc.setIdSite(idSite);
            cdc.setRefCDCArdia(cdcDto.getRefCDCArdia());
            cdc.setIndCDC(cdcDto.getIndCDC());
            cdc.setCreation(cdcDto.getCreation());

            if ("signé".equalsIgnoreCase(String.valueOf(cdcDto.getSignatureOk()))) {
                cdc.setSignatureOk(true);
            } else {
                cdc.setSignatureOk(false);
            }

            final CDC updateCdc = cdcRespositroy.save(cdc);
            return ResponseEntity.ok(updateCdc);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la modification du CDC.");
        }
    }

    @GetMapping("/getAllCdcSite")
    public ResponseEntity<List<Map<String, Object>>> getAllCdcSite() {
        List<CDC> cdcs = cdcRespositroy.findAll();
        List<Map<String, Object>> cdcMap = cdcs.stream()
                .map(cdc -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idCDC",cdc.getIdCDC());
                    map.put("nomCDC", cdc.getNomCDC());
                    map.put("refCDC", cdc.getRefCDC());
                    map.put("indCDC", cdc.getIndCDC());
                    if (cdc.getCreation() != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String creationDate = dateFormat.format(cdc.getCreation());
                        map.put("creation", creationDate);
                    } else {map.put("creation", "");}
                    if (cdc.getSignatureOk() != null && cdc.getSignatureOk())
                    {map.put("signatureOk", "signé");}
                    else {map.put("signatureOk", "non signé");}
                    map.put("refCDCArdia", cdc.getRefCDCArdia());
                    if (cdc.getIdSite()!= null) {
                        SITES sites =siteRep.findByIdSite(cdc.getIdCDC().intValue());
                        if (sites != null) {
                            map.put("site", sites.getNomSite());}
                        else {map.put("site","");}}
                    else {map.put("site", "");}
                    return map;})
                .collect(Collectors.toList());
        return ResponseEntity.ok(cdcMap);
    }

}
