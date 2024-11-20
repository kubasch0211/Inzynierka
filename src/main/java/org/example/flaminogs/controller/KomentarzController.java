package org.example.flaminogs.controller;

import org.example.flaminogs.entity.Komentarz;
import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.klasy.KomentarzRsp;
import org.example.flaminogs.requesty.CommentReq;
import org.example.flaminogs.service.KomentarzService;
import org.example.flaminogs.service.MultimediumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/komentarz")
public class KomentarzController {
    private final MultimediumService multimediumService;
    private final KomentarzService komentarzService;

    public KomentarzController(MultimediumService multimediumService, KomentarzService komentarzService) {
        this.multimediumService = multimediumService;
        this.komentarzService = komentarzService;
    }

    @PostMapping
    public ResponseEntity<KomentarzRsp> doKomentarz(@RequestBody CommentReq req){
        Komentarz komentarz=komentarzService.save(new Komentarz(req.getLogin(),req.getText(),req.getPostId(), LocalDateTime.now()));
        KomentarzRsp komentarzRsp=new KomentarzRsp(komentarz,req.getMultimedia());
        for (String multimedium : req.getMultimedia()){
            multimediumService.save(new Multimedium(null, komentarz.getId(), null,multimedium));
        }
        return new ResponseEntity<>(komentarzRsp, HttpStatus.OK);
    }
}
