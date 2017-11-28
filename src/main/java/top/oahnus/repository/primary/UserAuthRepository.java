package top.oahnus.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.primary.UserAuth;

/**
 * Created by oahnus on 2017/10/3
 * 0:50.
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findFirstByUsernameAndPassword(String username, String password);
}
