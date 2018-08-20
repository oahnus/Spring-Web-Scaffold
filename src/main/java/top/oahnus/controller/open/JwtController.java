package top.oahnus.controller.open;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oahnus on 2018/8/20
 * 13:35.
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @GetMapping("/test")
    public Object test() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("@securityService.hasAccess()")
    @GetMapping("/protected")
    public Object protectedRe() {
        return "success";
    }
}
