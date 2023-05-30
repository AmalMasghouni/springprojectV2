package com.spring.backproject.Controller;

import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.CdcRep;
import com.spring.backproject.Repository.DevRepository;
import com.spring.backproject.Repository.FonctionRepository;
import com.spring.backproject.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class ModifierDevController {
    @Autowired
    DevRepository devRepository;
    @Autowired
    CdcRep cdcRep;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    FonctionRepository fonctionRepository;
    @PutMapping("/modifier-dev/{id}")
    public ResponseEntity<?> modifierDev(@PathVariable("id") Long id, @RequestBody DevDto devDto) {
        // Vérifier si l'ID passé correspond à un enregistrement existant
        DEV dev = devRepository.findById(id).orElse(null);


        // Mettre à jour les propriétés du Dev avec les valeurs du DevDto
        dev.setNomDev(devDto.getNomDev());
        dev.setCdc(devDto.getCdc());
        dev.setNomDll(devDto.getNomDll());
        dev.setMenu(devDto.getMenu());
        dev.setUtilisateur(devDto.getUtilisateur());
        dev.setEcu(devDto.getEcu());
        dev.setMaj(devDto.getMaj());
        dev.setIdSite(devDto.getIdSite());
        dev.setDevPrecedent(devDto.getDevPrecedent());
        dev.setDevDuplique(devDto.getDevDuplique());
        dev.setDevEnCoursUtilisation(devDto.getDevEnCoursUtilisation());
        dev.setEtatDev(devDto.getEtatDev());
        dev.setTypeDev(devDto.getTypeDev());
        dev.setDevComment(devDto.getDevComment());
        dev.setNumBugzilla(devDto.getNumBugzilla());
        dev.setIdMajPrecedent(devDto.getIdMajPrecedent());
        dev.setCablesList(devDto.getCablesList());
        dev.setFonctionList(devDto.getFonctionList());
        dev.setVehidList(devDto.getVehidList());
        dev.setValidationList(devDto.getValidationList());

        // Enregistrer les modifications dans la base de données
        devRepository.save(dev);

        // Retourner une réponse réussie avec le Dev mis à jour
        return ResponseEntity.ok(dev);
    }
    @PutMapping("/modiiferDevByCDC")
    public ResponseEntity<?> modifDEVCDc(@RequestParam("idDev") Long idDev, @RequestParam("idCDC") Long idCDC){
        DEV dev = devRepository.findById(idDev).orElse(null);
        CDC cdc=cdcRep.findByIdCDC(idCDC);
        dev.setCdc(cdc);
        devRepository.save(dev);
        return ResponseEntity.ok(null);
    }
    @PutMapping("/modiDevVehicule")
    public ResponseEntity<?> modifDevVeh(@RequestParam("idDev") Long idDev, @RequestParam("codeVeh") Integer codeVeh){
        DEV dev = devRepository.findById(idDev).orElse(null);
        List<Vehid> vehid= vehiculeRepository.findByCodeVeh(codeVeh);
        for (Vehid v : vehid) {
            if (!dev.getVehidList().contains(v)) {
                dev.getVehidList().add(v);}}
        devRepository.save(dev);
        return ResponseEntity.ok(null);
    }
    @PutMapping("/modifierFonction")
    public ResponseEntity<?> modifFonction(@RequestParam("idDev") Long idDev, @RequestParam("idFonction") Integer idFonction){
        DEV dev =devRepository.findById(idDev).orElse(null);
        List<Fonction> fon=fonctionRepository.findByIdFonction(idFonction);
        for (Fonction f : fon) {
            if (!dev.getFonctionList().contains(f)) {
                dev.getFonctionList().add(f);}}
        devRepository.save(dev);
        return ResponseEntity.ok(null);
    }
    @DeleteMapping("/deletefonction")
    public ResponseEntity<?> deletefFonction(@RequestParam("idDev") Long idDev, @RequestParam("idFonction") Integer idFonction){
        DEV dev =devRepository.findById(idDev).orElse(null);
        if (dev != null) {
            List<Fonction> fonctions = fonctionRepository.findByIdFonction(idFonction);

            if (!fonctions.isEmpty()) {
                Fonction fonctionToDelete = fonctions.get(0); // Suppose qu'il n'y a qu'un seul élément avec l'ID donné
                dev.getFonctionList().remove(fonctionToDelete);
                devRepository.save(dev);
            }
        }
        return ResponseEntity.ok(null);
    }

}
