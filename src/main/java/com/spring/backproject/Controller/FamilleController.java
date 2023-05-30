package com.spring.backproject.Controller;

import com.spring.backproject.Models.Famille;
import com.spring.backproject.Models.FamilleDto;
import com.spring.backproject.Models.Marque;
import com.spring.backproject.Models.MarqueDto;
import com.spring.backproject.Repository.FamilleRep;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class FamilleController {
    @Autowired
    FamilleRep familleRep;
    @PostMapping("/ajout-famille")
    public ResponseEntity<?> AjoutFamille(@RequestBody FamilleDto familleDto){
        Famille famille=new Famille();
        famille.setNomFamille(familleDto.getNomFamille());
        famille.setDescrAnglais(famille.getDescrAnglais());
        famille.setPhrase(familleDto.getPhrase());
        famille.setOrdre(familleDto.getOrdre());


        famille.setUpdate(familleDto.getUpdate());
        famille.setSparePartsFilter(familleDto.getSparePartsFilter());
        famille.setGuidedMethFilter(familleDto.getGuidedMethFilter());
        famille.setDescription(familleDto.getDescription());
        famille.setIdFamilleHynes(familleDto.getIdFamilleHynes());
        famille.setDescriptionHaynes(familleDto.getDescriptionHaynes());
        Famille savedFamille=familleRep.save(famille);
        return ResponseEntity.ok(savedFamille);
    }
    @PutMapping("/modifier-famille/{idFamille}")
    public ResponseEntity<?> modifierFamille(@PathVariable Long idFamille, @RequestBody FamilleDto familleDto){
        Famille famille=familleRep.findById(idFamille).orElse(null);
        if(famille==null){return ResponseEntity.notFound().build();}
        famille.setNomFamille(familleDto.getNomFamille());
        famille.setDescrAnglais(famille.getDescrAnglais());
        famille.setPhrase(familleDto.getPhrase());
        famille.setOrdre(familleDto.getOrdre());
        famille.setUpdate(familleDto.getUpdate());
        famille.setSparePartsFilter(familleDto.getSparePartsFilter());
        famille.setGuidedMethFilter(familleDto.getGuidedMethFilter());
        famille.setDescription(familleDto.getDescription());
        famille.setIdFamilleHynes(familleDto.getIdFamilleHynes());
        famille.setDescriptionHaynes(familleDto.getDescriptionHaynes());
        final Famille updateFamille=familleRep.save(famille);
        return ResponseEntity.ok(updateFamille);
    }
    @GetMapping("/getAllFamille")
    public ResponseEntity<?>getAllFamille(){
        List<Famille> familles=familleRep.findAll();
        List<Map<String, Object>> familleMap =familles.stream()
                .map(famille -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idFamille",famille.getIdFamille());
                    map.put("nomFamille",famille.getNomFamille());
                    map.put("description",famille.getDescription());
                    map.put("descrAnglais",famille.getDescrAnglais());
                    map.put("ordre",famille.getOrdre());
                    if (famille.getUpdate()!=null){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String creationDate = dateFormat.format(famille.getUpdate());
                        map.put("update",creationDate);
                    }
                    else { map.put("update"," ");}

                    map.put("guidedMethFilter",famille.getGuidedMethFilter());
                    map.put("sparePartsFilter",famille.getSparePartsFilter());
                    map.put("idFamilleHynes",famille.getIdFamilleHynes());
                    map.put("descriptionHaynes",famille.getDescriptionHaynes());
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(familleMap);}
    @GetMapping("/getfamilleId/{idFamille}")
    public ResponseEntity<?>getFamilleById(@PathVariable Long idFamille){
        Famille familles=familleRep.findById(idFamille).orElse(null);
        return ResponseEntity.ok(familles);}
    @DeleteMapping("/delete-famille/{idFamille}")
    public ResponseEntity<?> DeletFamille(@PathVariable Long idFamille){
        familleRep.deleteById(idFamille);
        return ResponseEntity.ok(new AuthResponse(true,"Famille supprime"));
    }
    @GetMapping("/chercherparNomFamille")
    public ResponseEntity<?> rechercheNomFamille(@RequestBody Map<String, Object> requestBody) {
        String nomFamille= (String) requestBody.get("nomFamille");
        List<Famille> famille=familleRep.findAllByNomFamille(nomFamille);
        if (famille== null) {
            return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(famille);
    }
}
