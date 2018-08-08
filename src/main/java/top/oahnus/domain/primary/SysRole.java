package top.oahnus.domain.primary;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oahnus on 2018/8/8
 * 15:17.
 */
@Data
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String desc;

    @Transient
    private List<SysPermission> permissions;

    private Boolean enable = Boolean.FALSE;
}
