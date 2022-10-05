package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(@RequestParam(name="year", required = false) Integer year){
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

//    @GetMapping(value="whiskies/distillery/{age}")
//    public ResponseEntity<List<Whisky>> getDistilleryWhiskyByAge(@PathVariable int age){
//            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
//        }

    @GetMapping(value="whiskies/distillery")
    public ResponseEntity<List<Whisky>> getDistilleryWhiskyByAge
            (@RequestParam int age,
             @RequestParam Long distilleryId){
        return new ResponseEntity<>(whiskyRepository.findByAgeAndDistilleryId(age, distilleryId), HttpStatus.OK);
    }

    @GetMapping(value="whiskies/region/{region}")
    public ResponseEntity<List<Whisky>> getWhiskyByRegion(
            @PathVariable String region){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
    }
}
