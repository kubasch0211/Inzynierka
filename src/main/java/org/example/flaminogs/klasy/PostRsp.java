package org.example.flaminogs.klasy;

import org.example.flaminogs.entity.Post;

import java.util.List;

public class PostRsp {
    private Post post;
    private List<String> multimedia;
    private List<KomentarzRsp> komentarze;

    public PostRsp(Post post, List<String> multimedia, List<KomentarzRsp> komentarze) {
        this.post = post;
        this.multimedia = multimedia;
        this.komentarze = komentarze;
    }

    public PostRsp() {

    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<String> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<String> multimedia) {
        this.multimedia = multimedia;
    }

    public List<KomentarzRsp> getKomentarze() {
        return komentarze;
    }

    public void setKomentarze(List<KomentarzRsp> komentarze) {
        this.komentarze = komentarze;
    }
}
