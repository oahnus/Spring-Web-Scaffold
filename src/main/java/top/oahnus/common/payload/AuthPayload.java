package top.oahnus.common.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2017/10/3
 * 1:02.
 */
@Data
public class AuthPayload {
    @NotEmpty(message = "username不能为空")
    private String username;
    @NotEmpty(message = "password不能为空")
    private String password;
}
