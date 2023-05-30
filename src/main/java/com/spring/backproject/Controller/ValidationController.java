package com.spring.backproject.Controller;

import com.spring.backproject.Models.VERSION_MAJ;
import com.spring.backproject.Models.Validation;
import com.spring.backproject.Models.ValidationDto;
import com.spring.backproject.Repository.ValidationRep;
import com.spring.backproject.Repository.VersionMajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class ValidationController {
    @Autowired
    VersionMajRepository versionMajRepository;
    @Autowired
    ValidationRep validationRep;
    @PostMapping("/ajout-validation")
   public ResponseEntity<?> ajoutValidation(@RequestBody ValidationDto validationDto){
        Validation valid=new Validation();
        valid.setTypeValid(validationDto.getTypeValid());
        valid.setDateValid(validationDto.getDateValid());
        valid.setEtatValid(validationDto.getEtatValid());
        if(validationDto.getIdVer()!=null){
        VERSION_MAJ vers=versionMajRepository.findByNomVer(validationDto.getIdVer());
        Long versimid=vers.getIdVer();
        valid.setIdVer(String.valueOf(versimid));}
        validationRep.save(valid);
return ResponseEntity.ok(valid.getIdValid());
    }
@GetMapping("/validationversion")
public ResponseEntity<?> getValisation(){
    List<VERSION_MAJ> versionMajs=versionMajRepository.findAll();
    List<String> versionNom=new ArrayList<>();
    for(VERSION_MAJ v: versionMajs){
        versionNom.add(v.getNomVer());
    }
        return ResponseEntity.ok(versionNom);
}
}
