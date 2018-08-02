package top.oahnus.domain.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by oahnus on 2017/10/1
 * 21:39.
 */
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
}
