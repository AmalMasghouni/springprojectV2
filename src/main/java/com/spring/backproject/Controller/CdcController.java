package com.spring.backproject.Controller;

import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.SiteRep;
import com.spring.backproject.Repository.cdcRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class CdcController {
    @Autowired
    cdcRep cdcRespositroy;
    @Autowired
    SiteRep siteRep;
    @PostMapping("/ajout-cdc")
    public ResponseEntity<?> AjoutCdc(@RequestBody CdcDto cdcDto){
        CDC cdc=new CDC();
        Integer idS=siteRep.findIdSiteByNomSite(cdcDto.getIdSite());
        //Timestamp timestamp = Timestamp.valueOf(cdcDto.getCreation());
        //Timestamp timestamp = Timestamp.from(Instant.now());
        cdc.setNomCDC(cdcDto.getNomCDC());
        cdc.setRefCDC(cdcDto.getRefCDC());
        cdc.setIdSite(idS);
        cdc.setRefCDCArdia(cdcDto.getRefCDCArdia());
        cdc.setIndCDC(cdcDto.getIndCDC());

       // cdc.setCreation(timestamp);
        cdc.setCreation(cdcDto.getCreation());
        cdc.setSignatureOk(cdcDto.getSignatureOk());
        CDC savedcdc= cdcRespositroy.save(cdc);
       return ResponseEntity.ok(savedcdc);
    }
    @PutMapping("/modifier-cdc/{idCDC}")
    public ResponseEntity<?> modifieCdc(@PathVariable Long idCDC, @RequestBody CdcDto cdcDto){
        CDC cdc=cdcRespositroy.findById(idCDC).orElse(null);


        Integer idS=siteRep.findIdSiteByNomSite(cdcDto.getIdSite());
        if(cdc==null)
        {return ResponseEntity.notFound().build();}
        cdc.setNomCDC(cdcDto.getNomCDC());
        cdc.setRefCDC(cdcDto.getRefCDC());
        cdc.setIdSite(idS);
        cdc.setRefCDCArdia(cdcDto.getRefCDCArdia());
        cdc.setIndCDC(cdcDto.getIndCDC());
       cdc.setCreation(cdcDto.getCreation());
        if ("signé".equalsIgnoreCase(String.valueOf(cdcDto.getSignatureOk()))) {
            cdc.setSignatureOk(true);
        } else {
            cdc.setSignatureOk(false);
        }

        final CDC updateCdc=cdcRespositroy.save(cdc);
        return ResponseEntity.ok(updateCdc);
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
    @GetMapping("/rechercheparRefCdc")
    public ResponseEntity<?> rechercheparRefCdc (@RequestParam("refCDC") String refCDC) {

        List<CDC> cdcs=cdcRespositroy.findByRefCDC(refCDC);
        if (cdcs== null) {
            return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(cdcs);
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
                    map.put("creation", cdc.getCreation());

                    if (cdc.getSignatureOk() != null && cdc.getSignatureOk()) {
                        map.put("signatureOk", "signé");
                    } else {
                        map.put("signatureOk", "non signé");
                    }

                    map.put("refCDCArdia", cdc.getRefCDCArdia());

                    if (cdc.getIdSite() != null) {
                        Optional<SITES> optionalSite = siteRep.findById(Long.valueOf(cdc.getIdSite()));
                        if (optionalSite.isPresent()) {
                            map.put("idSite", optionalSite.get().getNomSite());
                        } else {
                            map.put("idSite", "N/A");
                        }
                    } else {
                        map.put("idSite", "N/A");
                    }

                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(cdcMap);
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
                    map.put("creation", cdc.getCreation());

                    if (cdc.getSignatureOk() != null && cdc.getSignatureOk()) {
                        map.put("signatureOk", "signé");
                    } else {
                        map.put("signatureOk", "non signé");
                    }

                    map.put("refCDCArdia", cdc.getRefCDCArdia());

                    if (cdc.getIdSite() != null) {
                        Optional<SITES> optionalSite = siteRep.findById(Long.valueOf(cdc.getIdSite()));
                        if (optionalSite.isPresent()) {
                            map.put("idSite", optionalSite.get().getNomSite());
                        } else {
                            map.put("idSite", "N/A");
                        }
                    } else {
                        map.put("idSite", "N/A");
                    }

                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(cdcMap);
    }

}
