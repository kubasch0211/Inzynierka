package org.example.flaminogs.controller;

import org.example.flaminogs.entity.Konto;
import org.example.flaminogs.klasy.Member;
import org.example.flaminogs.requesty.LoginReq;
import org.example.flaminogs.requesty.SetAdminReq;
import org.example.flaminogs.service.KontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/konta")
public class KontoController {
    private final KontoService kontoService;

    @Autowired
    public KontoController(KontoService kontoService) {
        this.kontoService = kontoService;
    }

    @PostMapping
    public ResponseEntity<Konto> createKonto(@RequestBody final Konto konto) {
        konto.setPassword(BCrypt.hashpw(konto.getPassword(), BCrypt.gensalt()));
        Konto savedKonto = kontoService.save(konto);
        return new ResponseEntity<>(savedKonto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReq loginRequest) {
        final Konto authenticatedKonto = kontoService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
        if (authenticatedKonto != null) {
            return new ResponseEntity<>(authenticatedKonto.getLogin(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> getIsAdmin(@RequestBody final String token) {
        return new ResponseEntity<>(isAdmin(token), HttpStatus.OK);
    }

    @PutMapping("/setAdmin")
    public ResponseEntity<Boolean> setAdmin(@RequestBody final SetAdminReq setAdminReq) {
        boolean isAdminDone = false;
        if(isAdmin(setAdminReq.getToken())){
            Konto konto = kontoService.findById(setAdminReq.getLogin());
            konto.setIsadmin(true);
            kontoService.save(konto);
            isAdminDone = true;
        }
        return new ResponseEntity<>(isAdminDone, HttpStatus.OK);
    }

    @GetMapping("/GetMembers")
    public ResponseEntity<List<Member>> getMembers() {
        return new ResponseEntity<>(convert(kontoService.findAll()), HttpStatus.OK);
    }

    private boolean isAdmin(final String token) {
        return kontoService.findById(token).isIsadmin();
    }


    public static List<Member> convert(List<Konto> konta) {
        return konta.stream()
                .map(konto -> new Member()
                        .login(konto.getLogin())
                        .name(konto.getName()))
                .collect(Collectors.toList());
    }
}
