package org.example.flaminogs.controller;

import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.entity.Post;
import org.example.flaminogs.klasy.KomentarzRsp;
import org.example.flaminogs.klasy.PostRsp;
import org.example.flaminogs.requesty.DeletePostReq;
import org.example.flaminogs.requesty.PostReq;
import org.example.flaminogs.security.JwtUtils;
import org.example.flaminogs.service.KomentarzService;
import org.example.flaminogs.service.KontoService;
import org.example.flaminogs.service.MultimediumService;
import org.example.flaminogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tablica")
public class PostController {
    private final PostService postService;
    private final MultimediumService multimediumService;
    private final KomentarzService komentarzService;
    private final KontoService kontoService;

    @Autowired
    private JwtUtils jwtUtils;

    public PostController(PostService postService, MultimediumService multimediumService, KomentarzService komentarzService, KontoService kontoService) {
        this.postService = postService;
        this.multimediumService = multimediumService;
        this.komentarzService = komentarzService;
        this.kontoService = kontoService;
    }

    @GetMapping
    public ResponseEntity<List<PostRsp>> getPosts() {
        return new ResponseEntity<>(mapPostsToMultimedia(postService.findAll(), multimediumService.findAll(), komentarzService.getAllKomentarze()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostRsp> doPost(@RequestBody PostReq req) {
        Post post = postService.save(new Post(jwtUtils.getLoginFromToken(req.getToken()), req.getText(), LocalDateTime.now()));
        PostRsp postRsp = new PostRsp(post, req.getMultimedia(), null);
        for (String multimedium : req.getMultimedia()) {
            multimediumService.save(new Multimedium(post.getId(), null, null, multimedium));
        }
        return new ResponseEntity<>(postRsp, HttpStatus.OK);
    }

    public static List<PostRsp> mapPostsToMultimedia(List<Post> posts, List<Multimedium> multimediaList, List<KomentarzRsp> komentarzeList) {
        List<PostRsp> result = new ArrayList<>();

        for (Post post : posts) {
            PostRsp temp = new PostRsp();
            temp.setPost(post);

            List<String> tempMultimedia = new ArrayList<>();
            List<KomentarzRsp> tempKomentarze = new ArrayList<>();
            for (Multimedium multimedia : multimediaList) {
                if (Objects.equals(multimedia.getIdpostu(), post.getId())) {
                    tempMultimedia.add(multimedia.getMultimedium());
                }
            }
            for (KomentarzRsp komentarz : komentarzeList) {
                if (Objects.equals(komentarz.getKomentarz().getIdposta(), post.getId())) {
                    tempKomentarze.add(komentarz);
                }
            }


            temp.setMultimedia(tempMultimedia);
            temp.setKomentarze(tempKomentarze);
            result.add(temp);
        }

        return result;
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@RequestBody DeletePostReq req) {
        if (isAdmin(req.getToken())) {
            postService.delete(req.getPostId());
        }
        return ResponseEntity.ok().build();
    }

    private boolean isAdmin(final String token) {
        return kontoService.findById(jwtUtils.getLoginFromToken(token)).isIsadmin();
    }

}
