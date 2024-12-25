package org.example.flaminogs.controller;

import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.entity.Wiadomosc;
import org.example.flaminogs.klasy.WiadomoscRsp;
import org.example.flaminogs.requesty.WiadomoscReq;
import org.example.flaminogs.requesty.WiadomosciGetReq;
import org.example.flaminogs.security.JwtUtils;
import org.example.flaminogs.service.MultimediumService;
import org.example.flaminogs.service.WiadomoscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/wiadomosc")
public class WiadomoscController {
    private final WiadomoscService wiadomoscService;
    private final MultimediumService multimediumService;

    @Autowired
    private JwtUtils jwtUtils;

    public WiadomoscController(WiadomoscService wiadomoscService, MultimediumService multimediumService) {
        this.wiadomoscService = wiadomoscService;
        this.multimediumService = multimediumService;
    }

    @PostMapping
    public ResponseEntity<WiadomoscRsp> doWiadomosc(@RequestBody WiadomoscReq req) {
        final Wiadomosc wiadomosc = wiadomoscService.save(new Wiadomosc()
                .loginsender(jwtUtils.getUsernameFromToken(req.getToken()))
                .text(req.getText())
                .date(LocalDateTime.now())
                .loginreceiver(req.getLoginreceiver()));

        final WiadomoscRsp rsp = new WiadomoscRsp(wiadomosc, req.getMultimedia());

        for (String multimedium : req.getMultimedia()) {
            multimediumService.save(new Multimedium(null, null, wiadomosc.getId(), multimedium));
        }
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @PostMapping("/rozmowa")
    public ResponseEntity<List<WiadomoscRsp>> getConversation(
            @RequestBody WiadomosciGetReq req) {
        return new ResponseEntity<>(mapWiadomosciToMultimedia(wiadomoscService.getConversation(jwtUtils.getUsernameFromToken(req.getToken()), req.getUser2()), multimediumService.findAll()), HttpStatus.OK);

    }

    public static List<WiadomoscRsp> mapWiadomosciToMultimedia(List<Wiadomosc> wiadomosci, List<Multimedium> multimediaList) {
        List<WiadomoscRsp> result = new ArrayList<>();

        for (Wiadomosc wiadomosc : wiadomosci) {
            WiadomoscRsp temp = new WiadomoscRsp();
            temp.setWiadomosc(wiadomosc);

            List<String> tempMultimedia = new ArrayList<>();
            for (Multimedium multimedia : multimediaList) {
                if (Objects.equals(multimedia.getIdwiadomosci(), wiadomosc.getId())) {
                    tempMultimedia.add(multimedia.getMultimedium());
                }
            }
            temp.setMultimedia(tempMultimedia);
            result.add(temp);
        }

        return result;
    }
}
