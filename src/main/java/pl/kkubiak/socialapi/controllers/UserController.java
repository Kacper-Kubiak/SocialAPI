package pl.kkubiak.socialapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kkubiak.socialapi.config.Router;
import pl.kkubiak.socialapi.dtos.UserDto;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;
import pl.kkubiak.socialapi.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(Router.USERS_URL)
    public User addNewUser(@RequestBody UserDto userDto) {
        return userService.addNewUser(userDto);
    }

    @GetMapping(Router.USER_URL)
    public User getUserById(@PathVariable long id) {
        return userService.getUserByID(id);
    }

    @GetMapping(Router.TIMELINE_URL)
    public List<Post> getTimelineByUserId(@PathVariable long id) {
        return userService.getTimelineByUserId(id);
    }

    @GetMapping(Router.WALL_URL)
    public List<Post> getWallByUserId(@PathVariable long id) {
        return userService.getWallByUserId(id);
    }

    @PostMapping(Router.FOLLOWS_URL)
    public void addNewFollow(@PathVariable long id, @RequestBody UserDto followUser) {
        userService.addNewFollow(id, followUser);
    }
}
