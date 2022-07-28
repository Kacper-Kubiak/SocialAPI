package pl.kkubiak.socialapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kkubiak.socialapi.dtos.UserDto;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;
import pl.kkubiak.socialapi.repository.PostRepository;
import pl.kkubiak.socialapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private PostService postService;

    @MockBean
    private UserRepository mockUserRepo;

    @Test
    void getUserByID() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("User");
        Mockito.when(mockUserRepo.findById(anyLong())).thenReturn(java.util.Optional.of(mockUser));

        User user = userService.getUserByID(1L);
        assertEquals(mockUser, user);
    }

    @Test
    void getWallByUserId() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("User");
        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setMessage("Test message");
        mockPost.setOwner(mockUser);
        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(mockPost);
        Mockito.when(postService.findAllByOwner(anyLong())).thenReturn(mockPosts);

        List<Post> posts = userService.getWallByUserId(1L);
        assertEquals(mockPosts, posts);
    }

    @Test
    void getTimelineByUserId() {
        User mockUser1 = new User();
        mockUser1.setId(1L);
        mockUser1.setName("User 1");

        User mockUser2 = new User();
        mockUser2.setId(2L);
        mockUser2.setName("User 2");
        mockUser2.getFollowing().add(mockUser1);

        Mockito.when(mockUserRepo.findById(2L)).thenReturn(java.util.Optional.of(mockUser2));

        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setMessage("Test message");
        mockPost.setOwner(mockUser1);
        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(mockPost);
        Mockito.when(postService.findAllByOwnerList(any())).thenReturn(mockPosts);

        List<Post> posts = userService.getTimelineByUserId(2L);
        assertEquals(mockPosts, posts);
    }

    @Test
    void addNewUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("User");
        Mockito.when(mockUserRepo.save(any())).thenReturn(mockUser);

        UserDto userDto = new UserDto();
        userDto.setName("User");
        User user = userService.addNewUser(userDto);
        assertEquals(mockUser, user);
    }

    @Test
    void addNewFollow() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("User");
        Mockito.when(mockUserRepo.findById(anyLong())).thenReturn(java.util.Optional.of(mockUser));

        UserDto followedUser = new UserDto();
        followedUser.setId(2L);
        followedUser.setName("User2");
        Mockito.when(mockUserRepo.save(any())).thenReturn(mockUser);

        userService.addNewFollow(1L, followedUser);
        assertEquals(mockUser.getFollowing().get(0).getId(), followedUser.getId());
        assertEquals(mockUser.getFollowing().get(0).getName(), followedUser.getName());
    }
}