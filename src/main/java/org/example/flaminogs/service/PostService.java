package org.example.flaminogs.service;

import org.example.flaminogs.entity.Post;
import org.example.flaminogs.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Post save(Post post) {
        return postRepository.save(post);
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public Post findById(int id) {
        return postRepository.getReferenceById(id);
    }
    public void delete(int id) {
        postRepository.deleteById(id);
    }
}
