package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.common.utils.MD5Helper;
import top.oahnus.domain.UserAuth;
import top.oahnus.repository.UserAuthRepo;

import java.util.UUID;

/**
 * Created by oahnus on 2017/10/3
 * 0:51.
 */
@Service
@Slf4j
public class AuthService{
    @Autowired
    private UserAuthRepo authRepository;
    @Autowired
    private SessionService sessionService;

    public TokenDto login(AuthPayload payload) {
        String username = payload.getUsername();
//        String password = MD5Helper.getMd5(payload.getPassword());
        String password = payload.getPassword();
        UserAuth auth = authRepository.findFirstByUsername(username);
        if (auth == null) {
            throw new AuthException(Message.USER_NOT_EXISTED);
        }
        if (!auth.getPassword().equals(password)) {
            throw new AuthException(Message.PASSWORD_FAILED);
        }
        String token = UUID.randomUUID().toString();
        sessionService.saveToken(token, auth.getUserId());
        log.debug("[AuthService.login] - payload = {}, token = {}", payload, token);
        return new TokenDto().token(token);
    }
}
