package pl.kkubiak.socialapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.kkubiak.socialapi.dtos.PostDto;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;
import pl.kkubiak.socialapi.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @MockBean
    private UserService userService;

    @MockBean
    private PostRepository mockPostRepo;

    @Test
    void createPost_Test() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test");
        when(userService.addNewUser(any())).thenReturn(mockUser);

        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setMessage("Test");
        mockPost.setOwner(mockUser);
        when(mockPostRepo.save(any())).thenReturn(mockPost);

        PostDto postDto = new PostDto();
        postDto.setMessage("test");
        postDto.setOwner(mockUser);
        Post post = postService.addNewPost(postDto);
        assertEquals(mockPost, post);
    }

    @Test
    void createPostWithoutUser_Test() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test");
        //when(userService.addNewUser(any())).thenReturn(mockUser);

        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setMessage("Test");
        mockPost.setOwner(mockUser);
        when(mockPostRepo.save(any())).thenReturn(mockPost);

        PostDto postDto = new PostDto();
        postDto.setMessage("test");
        mockUser.setId(null);
        postDto.setOwner(mockUser);
        Post post = postService.addNewPost(postDto);
        assertEquals(mockPost, post);
    }

    @Test
    void getPost_Test() {
        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setMessage("test");
        when(mockPostRepo.findById(anyLong())).thenReturn(java.util.Optional.of(mockPost));

        Post post = postService.getPostById(1L);
        assertEquals(mockPost, post);
    }

    @Test
    void findAllByOwner_Test() {
        List<Post> mockPostList = new ArrayList<>();

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test");

        Post mockPost1 = new Post();
        mockPost1.setId(1L);
        mockPost1.setMessage("Test1");
        mockPost1.setOwner(mockUser);
        mockPostList.add(mockPost1);

        Post mockPost2 = new Post();
        mockPost2.setId(2L);
        mockPost2.setMessage("Test2");
        mockPost2.setOwner(mockUser);
        mockPostList.add(mockPost2);

        when(mockPostRepo.findAllByOwnerIdOrderByCreatedDesc(anyLong())).thenReturn(mockPostList);

        List<Post> postList = postService.findAllByOwner(1L);
        assertEquals(mockPostList, postList);
    }

    @Test
    void findAllByOwnerList_Test() {
        List<Post> mockPostList = new ArrayList<>();

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Test");

        Post mockPost1 = new Post();
        mockPost1.setId(1L);
        mockPost1.setMessage("Test1");
        mockPost1.setOwner(mockUser);
        mockPostList.add(mockPost1);

        Post mockPost2 = new Post();
        mockPost2.setId(2L);
        mockPost2.setMessage("Test2");
        mockPost2.setOwner(mockUser);
        mockPostList.add(mockPost2);

        when(mockPostRepo.findAllByOwnerIsInOrderByCreatedDesc(any())).thenReturn(mockPostList);

        List<User> userList = new ArrayList<>();
        userList.add(mockUser);
        List<Post> postList = postService.findAllByOwnerList(userList);
        assertEquals(mockPostList, postList);
    }
}