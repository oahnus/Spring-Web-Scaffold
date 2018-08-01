package top.oahnus.domain.primary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by oahnus on 2017/10/3
 * 0:49.
 */
@Data
@Entity(name = "user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String pwdHash;
    private String salt;
    private Long userId;
}
