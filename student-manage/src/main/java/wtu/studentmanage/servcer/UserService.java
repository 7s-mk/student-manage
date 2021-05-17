package wtu.studentmanage.servcer;

import wtu.studentmanage.message.Page;
import wtu.studentmanage.pojo.User;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-10:48
 */
public interface UserService {

    Page<User> list();

    Page<User> list(String key, Integer page);

    Page<User> list(String key, Integer page, Integer size);

    User add(User user);

    User update(User user);

    User del(int id);
}
