package pl.kkubiak.socialapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kkubiak.socialapi.dtos.UserDto;
import pl.kkubiak.socialapi.exceptions.NotFoundException;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;
import pl.kkubiak.socialapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final PostService postService;

    @Autowired
    public UserService(final UserRepository userRepository, @Lazy final  PostService postService) {
        this.userRepository = userRepository;
        this.postService = postService;
    }

    public User getUserByID(long id) {
        return this.getUserEntity(id);
    }

    public List<Post> getTimelineByUserId(long id) {
        User user = getUserByID(id);
        return postService.findAllByOwnerList(user.getFollowing());
    }

    public List<Post> getWallByUserId(long id) {
        return postService.findAllByOwner(id);
    }

    public User addNewUser(UserDto userDto) {
        return userRepository.save(userDto.toEntity());
    }

    public void addNewFollow(long id, UserDto followUserDto) {
        User user = getUserByID(id);
        User followUser = getUserByID(followUserDto.getId());
        user.getFollowing().add(followUser);
        userRepository.save(user);
    }

    private User getUserEntity(long id) {
        Optional<User> userEntity = userRepository.findById(id);
        return userEntity.orElseThrow(() -> new NotFoundException("Could not find user: " + id));
    }
}
