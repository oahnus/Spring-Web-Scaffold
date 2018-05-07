package top.oahnus.controller.mixin;

import org.springframework.beans.factory.annotation.Autowired;
import top.oahnus.interfaces.LoginMixin;
import top.oahnus.service.session.RedisSessionService;

/**
 * Created by oahnus on 2017/10/17
 * 22:22.
 */
public class ControllerMixIn implements LoginMixin{
    @Autowired
    private RedisSessionService sessionService;

    public Long getUserId() {
        return sessionService.getUserId(getToken());
    }
}
