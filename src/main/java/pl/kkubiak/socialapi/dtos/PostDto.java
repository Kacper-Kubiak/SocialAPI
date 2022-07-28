package pl.kkubiak.socialapi.dtos;

import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;

import java.sql.Timestamp;

public class PostDto {
    private Long id;
    private String message;
    private Timestamp created;
    private User owner;

    public Post toEntity() {
        Post post = new Post();
        post.setId(this.getId());
        post.setMessage(this.getMessage());
        post.setCreated(this.getCreated());
        post.setOwner(this.getOwner());
        return post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
