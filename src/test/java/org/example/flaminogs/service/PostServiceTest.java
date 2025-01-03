package org.example.flaminogs.service;

import org.example.flaminogs.entity.Post;
import org.example.flaminogs.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository);
    }

    @Test
    void testSave() {
        String login = "user";
        String text = "This is a test post.";
        LocalDateTime date = LocalDateTime.now();

        Post post = new Post(login, text, date);
        post.setId(1);

        when(postRepository.save(post)).thenReturn(post);

        Post result = postService.save(post);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("user", result.getLogin());
        assertEquals("This is a test post.", result.getText());
        assertEquals(date, result.getDate());
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testFindAll() {
        Post post1 = new Post("user1", "Text 1", LocalDateTime.now());
        Post post2 = new Post("user2", "Text 2", LocalDateTime.now());

        List<Post> posts = List.of(post1, post2);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> result = postService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getLogin());
        assertEquals("Text 1", result.get(0).getText());
        assertEquals("user2", result.get(1).getLogin());
        assertEquals("Text 2", result.get(1).getText());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        int postId = 1;

        doNothing().when(postRepository).deleteById(postId);

        postService.delete(postId);

        verify(postRepository, times(1)).deleteById(postId);
    }
}
