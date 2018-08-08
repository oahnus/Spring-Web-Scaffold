package top.oahnus.domain.primary;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oahnus on 2018/8/8
 * 15:17.
 */
@Data
@Table(name = "sys_permission")
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String desc;

    private String permission;

    private Boolean enable = Boolean.FALSE;
}
