package com.spring.backproject.Controller;

import com.spring.backproject.Repository.MarqueRepo;
import com.spring.backproject.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class SelectController {
    @Autowired
    MarqueRepo marqueRepo;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @GetMapping("/selectMarque")
    public List<String> selectMarque(){return marqueRepo.selectMarque();}
    @GetMapping("/selectGroupeDeMarque")
    public List<String> selectGroupeDeMarque(){return vehiculeRepository.selectGroupeDeMarque();}

}
