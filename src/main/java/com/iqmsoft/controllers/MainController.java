package com.iqmsoft.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iqmsoft.entities.Kisi;
import com.iqmsoft.repositories.KisiRepository;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

	@Autowired
    private KisiRepository kisiRepository;

    @PostConstruct
    public void init(){
        Kisi kisi = new Kisi();
        kisi.setName("mert");
        kisi.setSoyad("cakmak");
        kisiRepository.save(kisi);

        Kisi kisi2 = new Kisi();
        kisi2.setName("admin");
        kisi2.setSoyad("user");
        kisiRepository.save(kisi2);

        Kisi kisi3 = new Kisi();
        kisi3.setName("test");
        kisi3.setSoyad("user");
        kisiRepository.save(kisi3);
        System.out.println("Added the records");
    }

  
    @GetMapping("{search}")
    public ResponseEntity<List<Kisi>> getKisi(@PathVariable String search){
    	System.out.println("Search " + search);
        List<Kisi> kisiler = kisiRepository.getByCustomQuery(search);
        System.out.println("Search " + kisiler.toString());
        return ResponseEntity.ok(kisiler);
    }

   
    @GetMapping("/contains/{search}")
    public ResponseEntity<List<Kisi>> getContainsKisi(@PathVariable String search){
    	System.out.println("Contains " + search);
        List<Kisi> kisiler = kisiRepository.findByNameContaining(search);
        System.out.println("Contains " + kisiler.toString());
        return ResponseEntity.ok(kisiler);
    }
}
