package org.example.flaminogs.controller;

import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.service.MultimediumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/multimedia")
public class MultimediumController {
    private final MultimediumService multimediumService;

     @Autowired
    public MultimediumController(MultimediumService multimediumService) {
        this.multimediumService = multimediumService;
    }
    @GetMapping()
    public ResponseEntity<List<Multimedium>> getMultimedium() {
        return new ResponseEntity<>(multimediumService.findAll(),HttpStatus.OK);
    }
}
