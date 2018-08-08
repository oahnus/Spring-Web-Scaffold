package top.oahnus.domain.primary;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oahnus on 2017/10/3
 * 0:49.
 */
@Data
@Table(name = "user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String pwdHash;
    private String salt;
    private Long userId;

    @Transient
    private List<SysRole> roles;
}
