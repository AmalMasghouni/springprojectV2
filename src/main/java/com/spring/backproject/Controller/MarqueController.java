package com.spring.backproject.Controller;

import com.spring.backproject.Models.Marque;
import com.spring.backproject.Models.MarqueDto;
import com.spring.backproject.Models.Vehid;
import com.spring.backproject.Repository.MarqueRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class MarqueController {
    @Autowired
    MarqueRepo marqueRepo;
   @PostMapping("/ajout-marque")
    public ResponseEntity<?> AjoutMarque(@RequestBody Marque marque){
       Marque savedMarque = marqueRepo.save(marque);
       return ResponseEntity.ok(savedMarque);
   }
   @DeleteMapping("/delete-marque/{codmar}")
    public ResponseEntity<?> DeletMarque(@PathVariable Integer codmar){
       marqueRepo.deleteById(codmar);
       return ResponseEntity.ok(new AuthResponse(true,"Marque supprimer"));
   }
    @GetMapping("/getAllMarque")
    public ResponseEntity<?>getAllMarque(){
       List<Marque> marques=marqueRepo.findAll();
       return ResponseEntity.ok(marques);}
    @GetMapping("getMarque/{codmar}")
    public ResponseEntity<?> getM(@PathVariable Integer codmar){
        Marque marque=marqueRepo.findById(codmar).orElse(null);
       return ResponseEntity.ok(marque);
    }

    @PutMapping("/modifier-marque/{codmar}")
    public ResponseEntity<?> modifierMarque(@PathVariable Integer codmar,@RequestBody MarqueDto marqueDto){
       Marque marque=marqueRepo.findById(codmar).orElse(null);
       if (marque ==null){return ResponseEntity.notFound().build();}
       marque.setProfil(marqueDto.getProfil());
       marque.setNommar(marqueDto.getNommar());
       marque.setGeo(marqueDto.getGeo());
       marque.setCible(marqueDto.getCible());

       final Marque updateMarque= marqueRepo.save(marque);
        return ResponseEntity.ok(updateMarque);
    }
    @GetMapping("/rechercheparNomMarque")
    public ResponseEntity<?> rechercheNomMarque (@RequestParam("nommar") String nommar) {

        List<Marque>marque=marqueRepo.findAllByNommar(nommar);
        if (marque == null) {
            return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(marque);
    }


}
