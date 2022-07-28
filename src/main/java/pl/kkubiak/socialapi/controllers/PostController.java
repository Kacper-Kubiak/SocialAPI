package pl.kkubiak.socialapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kkubiak.socialapi.config.Router;
import pl.kkubiak.socialapi.dtos.PostDto;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.services.PostService;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(Router.POSTS_URL)
    public Post addNewPost(@RequestBody PostDto postDto) {
        return postService.addNewPost(postDto);
    }

    @GetMapping(Router.POST_URL)
    public Post getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }
}
