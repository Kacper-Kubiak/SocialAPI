package pl.kkubiak.socialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kkubiak.socialapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
