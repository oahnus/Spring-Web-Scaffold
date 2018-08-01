package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Password;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.common.utils.PasswordHash;
import top.oahnus.domain.primary.UserAuth;
import top.oahnus.repository.primary.UserAuthRepository;
import top.oahnus.service.session.EhcacheSessionService;
import top.oahnus.service.session.RedisSessionService;

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
    private UserAuthRepository userAuthRepo;
    @Autowired
    private EhcacheSessionService sessionService;

    public TokenDto login(AuthPayload payload) {
        String username = payload.getUsername();
//        String password = MD5Helper.getMd5(payload.getPassword());
        String password = payload.getPassword();

        UserAuth auth = userAuthRepo.findByUsername(username);

        try {
            String correctHash = String.format("%s:%s:%s", 1000, auth.getSalt(), auth.getPwdHash());
            if (!PasswordHash.validatePassword(password, correctHash)) {
                throw new AuthException(Message.INVALID_USERNAME_OR_PASSWORD);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            e.printStackTrace();
        }
        String token = UUID.randomUUID().toString();
        log.debug("[AuthService.login] - payload={}, token={}, userId={}", payload, token, auth.getUserId());
//        sessionService.saveToken(auth.getUserId(), token);
        return new TokenDto().token(token);
    }
}
