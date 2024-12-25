package org.example.flaminogs.controller;

import org.example.flaminogs.entity.Komentarz;
import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.klasy.KomentarzRsp;
import org.example.flaminogs.requesty.CommentReq;
import org.example.flaminogs.requesty.DeleteCommentReq;
import org.example.flaminogs.security.JwtUtils;
import org.example.flaminogs.service.KomentarzService;
import org.example.flaminogs.service.KontoService;
import org.example.flaminogs.service.MultimediumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/komentarz")
public class KomentarzController {
    private final MultimediumService multimediumService;
    private final KomentarzService komentarzService;
    private final KontoService kontoService;

    @Autowired
    private JwtUtils jwtUtils;

    public KomentarzController(MultimediumService multimediumService, KomentarzService komentarzService, KontoService kontoService) {
        this.multimediumService = multimediumService;
        this.komentarzService = komentarzService;
        this.kontoService = kontoService;
    }

    @PostMapping
    public ResponseEntity<KomentarzRsp> doKomentarz(@RequestBody CommentReq req){
        Komentarz komentarz=komentarzService.save(new Komentarz(jwtUtils.getUsernameFromToken(req.getToken()),req.getText(),req.getPostId(), LocalDateTime.now()));
        KomentarzRsp komentarzRsp=new KomentarzRsp(komentarz,req.getMultimedia());
        for (String multimedium : req.getMultimedia()){
            multimediumService.save(new Multimedium(null, komentarz.getId(), null,multimedium));
        }
        return new ResponseEntity<>(komentarzRsp, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteKomentarz(@RequestBody DeleteCommentReq req){
        if(isAdmin(req.getToken())){
            komentarzService.delete(req.getCommentId());
        }
        return ResponseEntity.ok().build();
    }

    private boolean isAdmin(final String token) {
        return kontoService.findById(jwtUtils.getUsernameFromToken(token)).isIsadmin();
    }
}
