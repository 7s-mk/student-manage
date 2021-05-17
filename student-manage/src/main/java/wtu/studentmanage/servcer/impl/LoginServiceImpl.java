package wtu.studentmanage.servcer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wtu.studentmanage.dao.UserDao;
import wtu.studentmanage.message.MsgException;
import wtu.studentmanage.pojo.User;
import wtu.studentmanage.pojo.UserExample;
import wtu.studentmanage.servcer.LoginService;
import wtu.studentmanage.servcer.UserService;

import java.util.List;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-10:21
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao dao;

    @Autowired
    private UserService service;

    @Override
    public User login(String username, String password) {
        if (username == null || password == null) {
            throw new MsgException("用户名和密码不能为空");
        }

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = dao.selectByExample(example);
        if (users == null || users.size() < 1) {
            throw new MsgException("用户名或密码错误！");
        }
        return users.get(0);
    }

    @Override
    public User register(User user) {
        return service.add(user);
    }
}
