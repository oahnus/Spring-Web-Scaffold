package top.oahnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.UserAuth;

/**
 * Created by oahnus on 2017/10/3
 * 0:50.
 */
@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {
    UserAuth findFirstByUsernameAndPassword(String username, String password);
}
