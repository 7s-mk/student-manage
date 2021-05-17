package wtu.studentmanage.servcer;

import wtu.studentmanage.pojo.User;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-10:20
 */
public interface LoginService {

    User login(String username, String password);

    User register(User user);
}
