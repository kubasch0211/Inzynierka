package org.example.flaminogs.controller;

import org.example.flaminogs.entity.LokalizacjaIStatus;
import org.example.flaminogs.klasy.MapaRsp;
import org.example.flaminogs.requesty.MapaReq;
import org.example.flaminogs.security.JwtUtils;
import org.example.flaminogs.service.KontoService;
import org.example.flaminogs.service.LokalizacjaIStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mapa")
public class LokalizacjaIStatusController {
    private final LokalizacjaIStatusService lokalizacjaIStatusService;
    private final KontoService kontoService;
    @Autowired

    private JwtUtils jwtUtils;

    public LokalizacjaIStatusController(LokalizacjaIStatusService lokalizacjaIStatusService, KontoService kontoService) {
        this.lokalizacjaIStatusService = lokalizacjaIStatusService;
        this.kontoService = kontoService;
    }


    @GetMapping
    public ResponseEntity<List<MapaRsp>> getLocations(){
        List<MapaRsp> result = new ArrayList<>();

        for(LokalizacjaIStatus mapa : lokalizacjaIStatusService.findAll()){
            result.add(new MapaRsp(mapa,kontoService.findById(mapa.getLogin()).getName()));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LokalizacjaIStatus> setLocationAndStatus(@RequestBody MapaReq mapaReq) {
        LokalizacjaIStatus lokalizacjaIStatus = lokalizacjaIStatusService.getById(jwtUtils.getUsernameFromToken(mapaReq.getToken()));

        if (mapaReq.getStatus() != null) {
            lokalizacjaIStatus.setDatestatus(LocalDateTime.now());
            lokalizacjaIStatus.setStatus(mapaReq.getStatus());
        }

        if (mapaReq.getLatitude() != 0 || mapaReq.getLongitude() != 0) {
            lokalizacjaIStatus.setDatelocation(LocalDateTime.now());
            lokalizacjaIStatus.setLatitude(mapaReq.getLatitude());
            lokalizacjaIStatus.setLongitude(mapaReq.getLongitude());
        }

        return new ResponseEntity<>(lokalizacjaIStatusService.save(lokalizacjaIStatus), HttpStatus.OK);
    }

}
