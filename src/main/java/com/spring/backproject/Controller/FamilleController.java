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

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class FamilleController {
    @Autowired
    FamilleRep familleRep;
    @PostMapping("/ajout-famille")
    public ResponseEntity<?> AjoutFamille(@RequestBody Famille famille){
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
        famille.setOrdre(famille.getOrdre());
        famille.setUpdate(new Date());
        famille.setSparePartsFilter(familleDto.getSparePartsFilter());
        famille.setGuidedMethFilter(familleDto.getGuidedMethFilter());
        famille.setDescription(familleDto.getDescription());
        final Famille updateFamille=familleRep.save(famille);
        return ResponseEntity.ok(updateFamille);
    }
    @GetMapping("/getAllFamille")
    public ResponseEntity<?>getAllFamille(){
        List<Famille> familles=familleRep.findAll();
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
