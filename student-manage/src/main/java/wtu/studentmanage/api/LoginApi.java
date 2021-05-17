package wtu.studentmanage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtu.studentmanage.message.HttpContext;
import wtu.studentmanage.message.RestMsg;
import wtu.studentmanage.pojo.User;
import wtu.studentmanage.servcer.LoginService;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-9:12
 */
@RestController
@RequestMapping("/api/v1/login")
public class LoginApi {
    @Autowired
    LoginService service;

    /**
     * 登录
     */
    @PostMapping
    public RestMsg<User> login(String username, String password) {
        try {
            User user = service.login(username, password);
            HttpContext.setUser(user);
            return new RestMsg<>(user);
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    /**
     * 退出登录
     *
     * @return 退出
     */
    @GetMapping("/logout")
    public RestMsg<User> logout() {
        try {
            if (HttpContext.getUser() == null) {
                return new RestMsg<>(null, "用户还未登录");
            }
            HttpContext.rmuser();
            return new RestMsg<>();
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public RestMsg<User> register(User user) {
        try {
            return new RestMsg<>(service.register(user));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    /**
     * 获取登录用户
     */
    @GetMapping("/me")
    public RestMsg<User> checkMe() {
        try {
            User user = HttpContext.getUser();
            if (user == null) {
                return new RestMsg<>(null, "用户未登录");
            }
            return new RestMsg<>(user);
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    /**
     * 同上
     *
     * @return 同上
     */
    @GetMapping
    public RestMsg<User> info() {
        return checkMe();
    }


}
