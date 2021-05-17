package wtu.studentmanage.servcer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wtu.studentmanage.dao.UserDao;
import wtu.studentmanage.message.HttpContext;
import wtu.studentmanage.message.MsgException;
import wtu.studentmanage.message.Page;
import wtu.studentmanage.pojo.User;
import wtu.studentmanage.pojo.UserExample;
import wtu.studentmanage.servcer.UserService;

import java.util.List;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-10:50
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Override
    public Page<User> list() {
        User me = HttpContext.getUser();
        if (me == null) {
            throw new MsgException("权限不足");
        }
        List<User> users = dao.selectByExample(null);
        return Page.rest(users, null, 1, users.size(), users.size());
    }

    @Override
    public Page<User> list(String key, Integer page) {
        return list(key, page, 20);
    }

    @Override
    public Page<User> list(String key, Integer page, Integer size) {
        User me = HttpContext.getUser();
        if (me == null) {
            throw new MsgException("权限不足");
        }

        UserExample example = new UserExample();
        key = key == null ? "" : key;
        key = "%" + key + "%";
        example.createCriteria().andNameLike(key);
        example.or().andUsernameLike(key);
        long all = dao.countByExample(example);
        if (page != null && size != null) {
            size = size < 1 ? 10 : size;
            example.setLimit(size);
            page = Math.max(page, 1);
            example.setOffset((long) (size * (page - 1)));
        }
        return Page.rest(dao.selectByExample(example), key, page, size, all);
    }

    @Override
    public User add(User user) {
        if (user == null || user.getUsername() == null) {
            throw new MsgException("用户名不能为空");
        }
        if (user.getPassword() == null) {
            throw new MsgException("密码不能为空");
        }
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        if (dao.countByExample(example) != 0) {
            throw new MsgException("用户名已存在");
        }
        if (dao.insertSelective(user) != 1) {
            throw new MsgException("操作失败");
        }
        return user;
    }

    @Override
    public User update(User user) {
        User me = HttpContext.getUser();
        if (me == null) {
            throw new MsgException("权限不足");
        }
        User us = dao.selectByPrimaryKey(user.getId());
        if (us == null) {
            throw new MsgException("该用户不存在");
        }

        dao.updateByPrimaryKeySelective(user);

        // 如果是自己就替换
        if (me.getId().equals(user.getId())) {
            HttpContext.setUser(dao.selectByPrimaryKey(me.getId()));
        }

        return dao.selectByPrimaryKey(user.getId());
    }

    @Override
    public User del(int id) {
        User me = HttpContext.getUser();
        if (me == null) {
            throw new MsgException("权限不足");
        }
        if (me.getId() == id) {
            throw new MsgException("无法删除自己");
        }

        User us = dao.selectByPrimaryKey(id);
        if (us == null) {
            throw new MsgException("该用户不存在");
        }
        dao.deleteByPrimaryKey(id);
        return us;
    }
}
