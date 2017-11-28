package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.common.utils.MD5Helper;
import top.oahnus.domain.primary.UserAuth;
import top.oahnus.repository.primary.UserAuthRepository;

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
        String username = payload.getUsername();
        String password = MD5Helper.getMd5(payload.getPassword());
        UserAuth auth = authRepository.findFirstByUsernameAndPassword(username, password);
        if (auth == null) {
            throw new AuthException(Message.NO_AUTH);
        }
        String token = UUID.randomUUID().toString();
        System.out.println(sessionService.saveToken(auth.getUserId(), token));
//        System.out.println(sessionService.getSessionUserId());
        return new TokenDto().token(token).userId(auth.getUserId());
    }
}
