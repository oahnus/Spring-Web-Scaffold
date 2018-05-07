package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.domain.primary.UserAuth;
import top.oahnus.repository.primary.UserAuthRepository;
import top.oahnus.service.session.RedisSessionService;

import java.util.UUID;

/**
 * Created by oahnus on 2017/10/3
 * 0:51.
 */
@Service
@Slf4j
public class AuthService{
    @Autowired
    private UserAuthRepository userAuthRepo;
    @Autowired
    private RedisSessionService sessionService;

    public TokenDto login(AuthPayload payload) {
        String username = payload.getUsername();
//        String password = MD5Helper.getMd5(payload.getPassword());
        String password = payload.getPassword();
        UserAuth auth = userAuthRepo.findFirstByUsername(username);
        if (auth == null) {
            throw new AuthException(Message.USER_NOT_EXISTED);
        }
        if (!auth.getPassword().equals(password)) {
            throw new AuthException(Message.PASSWORD_FAILED);
        }
        String token = UUID.randomUUID().toString();
        sessionService.saveToken(auth.getUserId(), token);
        log.debug("[AuthService.login] - payload = {}, token = {}", payload, token);
        return new TokenDto().token(token);
    }
}
