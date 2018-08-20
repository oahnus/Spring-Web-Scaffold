package top.oahnus.domain.primary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

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
@Table(name = "user_auth")
public class UserAuth implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @JsonIgnore
    private String pwdHash;
    @JsonIgnore
    private String salt;
    private Long userId;
    private Date lastPasswordReset;

    @Transient
    private List<SysRole> roles;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuth() {}

    public UserAuth(Long id, String username, String pwdHash, String salt, Long userId, Date lastPasswordReset, List<SysRole> roles, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.pwdHash = pwdHash;
        this.salt = salt;
        this.userId = userId;
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
        return String.format("%s:%s:%s", 1000, salt, pwdHash);
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
