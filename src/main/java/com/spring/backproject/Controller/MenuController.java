package com.spring.backproject.Controller;

import com.spring.backproject.Models.Menu;
import com.spring.backproject.Models.Vehid;
import com.spring.backproject.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class MenuController {
@Autowired
    MenuRepository menuRepository;
@GetMapping("/getMenuByid/{idMenu}")
public ResponseEntity<?> getMenu(@PathVariable("idMenu") Long idMenu){
    Menu menuList=menuRepository.findById(idMenu).orElse(null);
    return ResponseEntity.ok(menuList);
}

}
