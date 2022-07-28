package pl.kkubiak.socialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kkubiak.socialapi.models.Post;
import pl.kkubiak.socialapi.models.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOwnerIdOrderByCreatedDesc(long id);
    List<Post> findAllByOwnerIsInOrderByCreatedDesc(List<User> userList);
}
