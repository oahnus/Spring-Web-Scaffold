package top.oahnus.domain.primary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.annotation.ColumnType;
import top.oahnus.common.enums.ESex;
import top.oahnus.common.utils.PasswordHash;
import top.oahnus.mybatis.handler.ESexHandler;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by oahnus on 2017/10/3
 * 0:49.
 */
@Data
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @JsonIgnore
    private String pwdHash;
    @JsonIgnore
    private String salt;
    // @ColumnType tk.mapper mybatis通用mapper提供的注解，指明enum使用的typehandler
    @ColumnType(typeHandler = ESexHandler.class)
    private ESex sex;

    private Date lastPasswordReset;

    @Transient
    private List<SysRole> roles;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User() {}

    public User(Long id, String username, String pwdHash, String salt, Date lastPasswordReset, List<SysRole> roles, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.pwdHash = pwdHash;
        this.salt = salt;
        this.lastPasswordReset = lastPasswordReset;
        this.roles = roles;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        return roles.stream()
                .map(SysRole::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return String.format("%s:%s:%s", PasswordHash.PBKDF2_ITERATIONS, salt, pwdHash);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
