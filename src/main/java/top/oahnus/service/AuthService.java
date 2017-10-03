package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.domain.UserAuth;
import top.oahnus.repository.UserAuthRepository;

import java.util.UUID;

/**
 * Created by oahnus on 2017/10/3
 * 0:51.
 */
@Service
public class AuthService {
    @Autowired
    private UserAuthRepository authRepository;
    @Autowired
    private SessionService sessionService;

    public TokenDto login(AuthPayload payload) {
        UserAuth auth = authRepository.findFirstByUsernameAndPassword(payload.getUsername(), payload.getPassword());
        if (auth == null) {
            throw new AuthException(Message.NO_AUTH);
        }
        String token = UUID.randomUUID().toString();
        sessionService.saveToken(auth.getUserId(), token);
        return new TokenDto().token(token).userId(auth.getUserId());
    }
}
