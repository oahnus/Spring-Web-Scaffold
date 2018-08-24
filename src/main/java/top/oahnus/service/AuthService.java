package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.common.utils.PasswordHash;
import top.oahnus.domain.primary.User;
import top.oahnus.mapper.primary.UserMapper;
import top.oahnus.service.session.EhcacheSessionService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

/**
 * Created by oahnus on 2017/10/3
 * 0:51.
 */
@Service
@Slf4j
public class AuthService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EhcacheSessionService sessionService;

    /**
     * 使用自定义token, redis维护token时使用此方法作为登录入口,
     * 使用Spring security及jwt时，不走此方法，并且不使用SessionService
     * @param payload
     * @return
     */
    public TokenDto login(AuthPayload payload) {
        String username = payload.getUsername();
//        String password = MD5Helper.getMd5(payload.getPassword());
        String password = payload.getPassword();

        User user = userMapper.findFirstByUsername(username);

        try {
            String correctHash = String.format("%s:%s:%s", 1000, user.getSalt(), user.getPwdHash());
            if (!PasswordHash.validatePassword(password, correctHash)) {
                throw new AuthException(Message.INVALID_USERNAME_OR_PASSWORD);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            e.printStackTrace();
        }
        String token = UUID.randomUUID().toString();
        log.debug("[AuthService.login] - payload={}, token={}, userId={}", payload, token, user.getId());
//        sessionService.saveToken(auth.getUserId(), token);
        return new TokenDto().token(token);
    }
}
