package top.oahnus.common.dto;

import lombok.Data;
import top.oahnus.service.session.SessionService;

import java.util.Date;

/**
 * Created by oahnus on 2017/10/3
 * 0:54.
 */
@Data
public class TokenDto {
    private String token;
    private Date expire;
    private Date createAt;
    private Long userId;

    public TokenDto() {
        this.createAt = new Date();
        this.expire = new Date(this.createAt.getTime() + SessionService.EXPIRE * 86400000);
    }

    public TokenDto token(String token) {
        this.token = token;
        return this;
    }

    public TokenDto userId(Long userId) {
        this.userId = userId;
        return this;
    }
}
