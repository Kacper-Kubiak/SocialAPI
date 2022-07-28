package pl.kkubiak.socialapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kkubiak.socialapi.dtos.PostDto;
import pl.kkubiak.socialapi.dtos.UserDto;
import pl.kkubiak.socialapi.exceptions.NotFoundException;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;
import pl.kkubiak.socialapi.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostService(final PostRepository postRepository, @Lazy final UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post getPostById(long id) {
        return this.getPostEntity(id);
    }

    public Post addNewPost(PostDto postDto) {
        if(postDto.getOwner().getId() == null && postDto.getOwner().getName() != null) {
            User user = userService.addNewUser(new UserDto().buildDto(postDto.getOwner()));
            postDto.setOwner(user);
        }
        return postRepository.save(postDto.toEntity());
    }

    public List<Post> findAllByOwner(long userId) {
        return postRepository.findAllByOwnerIdOrderByCreatedDesc(userId);
    }

    public List<Post> findAllByOwnerList(List<User> userList) {
        return postRepository.findAllByOwnerIsInOrderByCreatedDesc(userList);
    }

    private Post getPostEntity(long id) {
        Optional<Post> postEntity = postRepository.findById(id);
        return postEntity.orElseThrow(() -> new NotFoundException("Could not find post: " + id));
    }
}
