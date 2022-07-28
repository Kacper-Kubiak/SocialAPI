package pl.kkubiak.socialapi.dtos;

import pl.kkubiak.socialapi.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private List<User> following = new ArrayList<>();

    public UserDto buildDto(User user) {
        if(user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setFollowing(user.getFollowing());
        }
        return this;
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setFollowing(this.getFollowing());
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}
