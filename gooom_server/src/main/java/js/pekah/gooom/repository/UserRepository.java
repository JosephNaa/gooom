package js.pekah.gooom.repository;

import js.pekah.gooom.exception.ResourceNotFoundException;
import js.pekah.gooom.model.User;
import js.pekah.gooom.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NotBlank String userName);
    Optional<User> findByEmail(@NotBlank String email);

    Boolean existsByUsername(@NotBlank String userName);

    Boolean existsByEmail(@NotBlank String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    default User getUser(UserPrincipal currentUser) {return getUserByName(currentUser.getUsername());}

    default User getUserByName(String userName) {
        return findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", userName));
    }
}
