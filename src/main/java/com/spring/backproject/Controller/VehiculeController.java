package com.spring.backproject.Controller;

import com.spring.backproject.Exception.ResourceNotFoundException;
import com.spring.backproject.Models.Marque;
import com.spring.backproject.Models.Vehid;
import com.spring.backproject.Models.VehidDto;
import com.spring.backproject.Repository.MarqueRepo;
import com.spring.backproject.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class VehiculeController {
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    MarqueRepo marqueRepo;
    @PostMapping("/ajout-vehicule")
    public ResponseEntity<?> AddVehicule(@RequestBody VehidDto vehidDto){
        Vehid vehicule=new Vehid();
        Marque marque = marqueRepo.findByNommar(vehidDto.getMarque())
                .orElseThrow(() -> new ResourceNotFoundException("Marque introuvable pour ce nom : " + vehidDto.getMarque()));
        vehicule.setNomVeh(vehidDto.getNomVeh());
        vehicule.setNomInterne(vehidDto.getNomInterne());
        vehicule.setCodeVeh(vehidDto.getCodeVeh());
        vehicule.setGrpMarq(vehidDto.getGrpMarq());
        vehicule.setRemarque(vehicule.getRemarque());
        vehicule.setMsgDiag(vehidDto.getMsgDiag());
        vehicule.setFro(vehicule.getFro());
        vehicule.setT(vehidDto.getT());
        vehicule.setUpd(new Date());
        vehicule.setTestGlobal(vehidDto.getTestGlobal());
        vehicule.setAvertissHybrid(vehidDto.getAvertissHybrid());
        vehicule.setOnlyElec(vehidDto.getOnlyElec());
        vehicule.setAvertissElec(vehidDto.getAvertissElec());
        vehicule.setMarque(marque);
        vehiculeRepository.save(vehicule);
        return ResponseEntity.ok(new AuthResponse(true,"vehicule ajouté"));
    }
    @DeleteMapping("/delete-vehicule/{codeVeh}")
    public ResponseEntity<?> deleteVoiture(@PathVariable Integer codeVeh ){
        vehiculeRepository.deleteById(codeVeh);
          return ResponseEntity.ok(new AuthResponse(true,"vehicule supprimer"));

    }
    @GetMapping("/getVehicule/{codeVeh}")
    public ResponseEntity<?> getVehiculebuid(@PathVariable Integer codeVeh ){

            Vehid vehicule = vehiculeRepository.findById(codeVeh).orElse(null);
            if (vehicule == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> vehiculeMap = new HashMap<>();
            vehiculeMap.put("codeVeh", vehicule.getCodeVeh());
            vehiculeMap.put("nomVeh", vehicule.getNomVeh());
            vehiculeMap.put("nomInterne", vehicule.getNomInterne());
            vehiculeMap.put("testGlobal", vehicule.getTestGlobal());
            vehiculeMap.put("msgDiag", vehicule.getMsgDiag());
            vehiculeMap.put("grpMarq", vehicule.getGrpMarq());
            vehiculeMap.put("fro", vehicule.getFro());
            vehiculeMap.put("t", vehicule.getT());
            vehiculeMap.put("upd", vehicule.getUpd());
            vehiculeMap.put("remarque", vehicule.getRemarque());
            vehiculeMap.put("onlyElec", vehicule.getOnlyElec());
            vehiculeMap.put("avertissElec", vehicule.getAvertissElec());
            vehiculeMap.put("avertissHybrid", vehicule.getAvertissHybrid());
            if (vehicule.getMarque() != null) {
                vehiculeMap.put("marque", vehicule.getMarque().getNommar());
            } else {
                vehiculeMap.put("marque", "");
            }

            return ResponseEntity.ok(vehiculeMap);




     }

    @PutMapping("/modifier-vehicule/{codeVeh}")
    public ResponseEntity<Vehid> updateVehicule(@PathVariable(value = "codeVeh") Integer codeVeh,@RequestBody VehidDto vehidDto){
        Vehid vehicule = vehiculeRepository.findById(codeVeh).orElse(null);
        if (vehicule == null) {
            return ResponseEntity.notFound().build();
        }

        Marque marque = marqueRepo.findByNommar(vehidDto.getMarque())
                .orElseThrow(() -> new ResourceNotFoundException("Marque introuvable pour ce nom : " + vehidDto.getMarque()));

        vehicule.setNomVeh(vehidDto.getNomVeh());
        vehicule.setNomInterne(vehidDto.getNomInterne());
        vehicule.setGrpMarq(vehidDto.getGrpMarq());
        vehicule.setRemarque(vehicule.getRemarque());
        vehicule.setMsgDiag(vehidDto.getMsgDiag());
        vehicule.setFro(vehicule.getFro());
        vehicule.setT(vehidDto.getT());
        vehicule.setUpd(new Date());
        vehicule.setTestGlobal(vehidDto.getTestGlobal());
        vehicule.setAvertissHybrid(vehidDto.getAvertissHybrid());
        vehicule.setOnlyElec(vehidDto.getOnlyElec());
        vehicule.setAvertissElec(vehidDto.getAvertissElec());
        vehicule.setMarque(marque);

        final Vehid updatedVehicule = vehiculeRepository.save(vehicule);
        return ResponseEntity.ok(updatedVehicule);
    }


    @GetMapping("/getAllVehicule")
    public ResponseEntity<?>getAllVehicule(){
        List<Vehid> vehicule=vehiculeRepository.findAll();
        return ResponseEntity.ok(vehicule);}
    @GetMapping("/getAllVehiculeMarque")
    public ResponseEntity<List<Map<String, Object>>> getAllVehiculeMarque() {
        List<Vehid> vehicules = vehiculeRepository.findAll();

        List<Map<String, Object>> vehiculeMap = vehicules.stream()
                .map(vehicule -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("codeVeh", vehicule.getCodeVeh());
                    map.put("nomVeh", vehicule.getNomVeh());
                    map.put("nomInterne", vehicule.getNomInterne());
                    map.put("testGlobal", vehicule.getTestGlobal());
                    map.put("msgDiag", vehicule.getMsgDiag());
                    map.put("grpMarq", vehicule.getGrpMarq());
                    map.put("fro", vehicule.getFro());
                    map.put("t", vehicule.getT());
                    map.put("upd", vehicule.getUpd());
                    map.put("remarque", vehicule.getRemarque());
                    map.put("onlyElec", vehicule.getOnlyElec());
                    map.put("avertissElec", vehicule.getAvertissElec());
                    map.put("avertissHybrid", vehicule.getAvertissHybrid());
                    if (vehicule.getMarque() != null) {
                        map.put("marque", vehicule.getMarque().getNommar());
                    } else {
                        map.put("marque", "");
                    }
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehiculeMap);
    }


    @GetMapping("/chercherVehiculeparNom/{nomVeh}")
    public ResponseEntity<List<Map<String, Object>>> getVehiculeParNOM(@PathVariable("nomVeh") String nomVeh)  {
         //   @RequestBody Map<String, Object> requestBody
       // String nomVeh = (String) requestBody.get("nomVeh");
        List<Vehid> Listvehicule = vehiculeRepository.findByNomVeh(nomVeh);

        if (Listvehicule == null) {

            return ResponseEntity.notFound().build();
        }
        List<Map<String, Object>> vehiculeMap = Listvehicule.stream()

                .map(vehid -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("codeVeh", vehid.getCodeVeh());
                    map.put("nomVeh", vehid.getNomVeh());
                    map.put("nomInterne", vehid.getNomInterne());
                    map.put("testGlobal", vehid.getTestGlobal());
                    map.put("msgDiag", vehid.getMsgDiag());
                    map.put("grpMarq", vehid.getGrpMarq());
                    map.put("fro", vehid.getFro());
                    map.put("t", vehid.getT());
                    map.put("upd", vehid.getUpd());
                    map.put("remarque", vehid.getRemarque());
                    map.put("onlyElec", vehid.getOnlyElec());
                    map.put("avertissElec", vehid.getAvertissElec());
                    map.put("avertissHybrid", vehid.getAvertissHybrid());
                    if (vehid.getMarque() != null) {
                        map.put("marque", vehid.getMarque().getNommar());
                    } else {
                        map.put("marque", "");
                    }
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehiculeMap);
    }




}

