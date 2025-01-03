package org.example.flaminogs.controller;

import jakarta.validation.Valid;
import org.example.flaminogs.entity.Konto;
import org.example.flaminogs.klasy.Member;
import org.example.flaminogs.requesty.KontoReq;
import org.example.flaminogs.requesty.LoginReq;
import org.example.flaminogs.requesty.SetAdminReq;
import org.example.flaminogs.security.JwtUtils;
import org.example.flaminogs.service.KontoService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<Konto> createKonto(@Valid @RequestBody final KontoReq req) {
        Konto konto = new Konto();
        konto.setLogin(req.getLogin());
        konto.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
        konto.setName(req.getName());
        konto.setIsadmin(false);
        Konto savedKonto = kontoService.save(konto);
        return new ResponseEntity<>(savedKonto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginReq loginRequest) {
        final String token = kontoService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/isAdmin")
    public ResponseEntity<Boolean> getIsAdmin(@Valid @RequestBody final String token) {
        return new ResponseEntity<>(isAdmin(token), HttpStatus.OK);
    }

    @PutMapping("/setAdmin")
    public ResponseEntity<Boolean> setAdmin(@Valid @RequestBody final SetAdminReq setAdminReq) {
        boolean isAdminDone = false;
        if (isAdmin(setAdminReq.getToken())) {
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
        return kontoService.findById(jwtUtils.getLoginFromToken(token)).isIsadmin();
    }


    public static List<Member> convert(List<Konto> konta) {
        return konta.stream()
                .map(konto -> new Member()
                        .isadmin(konto.isIsadmin())
                        .login(konto.getLogin())
                        .name(konto.getName()))
                .collect(Collectors.toList());
    }
}
