package wtu.studentmanage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wtu.studentmanage.message.HttpContext;
import wtu.studentmanage.message.Page;
import wtu.studentmanage.message.RestMsg;
import wtu.studentmanage.pojo.User;
import wtu.studentmanage.servcer.UserService;


/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-10:59
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    @Autowired
    private UserService service;

    @GetMapping
    public RestMsg<User> me() {
        try {
            return new RestMsg<>(HttpContext.getUser());
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @GetMapping("/list")
    public RestMsg<Page<User>> list(String key, Integer page, Integer size) {
        try {
            return new RestMsg<>(service.list(key, page, size));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @DeleteMapping
    public RestMsg<User> del(int id) {
        try {
            return new RestMsg<>(service.del(id));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @PostMapping
    public RestMsg<User> add(User user) {
        try {
            return new RestMsg<>(service.add(user));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @PutMapping
    public RestMsg<User> modify(User user) {
        try {
            return new RestMsg<>(service.update(user));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

}
