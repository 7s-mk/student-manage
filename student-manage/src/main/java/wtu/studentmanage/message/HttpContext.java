package wtu.studentmanage.message;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wtu.studentmanage.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述： Http 上下文对象
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-10:13
 */
public class HttpContext {

    public static String USER_SAVE_NAME = "_USER_SAVE_NAME_";

    /**
     * 获取请求对象
     *
     * @return 请求对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 获取当前 response 对象
     *
     * @return response
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getResponse();
    }


    /**
     * 获取域对象
     *
     * @param name name
     * @return 域
     */
    public static Object get(String name) {
        return getRequest() == null ? null : getRequest().getSession().getAttribute(name);
    }

    /**
     * 设置域对象
     *
     * @param name 域
     * @param obj  obj
     */
    public static void set(String name, Object obj) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            request.getSession().setAttribute(name, obj);
        }
    }

    /**
     * 移除域对象
     *
     * @param name name
     */
    public static void remove(String name) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            request.getSession().removeAttribute(name);
        }
    }


    /**
     * 设置用户
     *
     * @param user 登录用户
     */
    public static void setUser(User user) {
        set(USER_SAVE_NAME, user);
    }


    /**
     * 获取用户
     *
     * @return 登录用户
     */
    public static User getUser() {
        Object o = get(USER_SAVE_NAME);
        if (!(o instanceof User)) {
            return null;
        }
        return (User) o;
    }

    /**
     * 退出登录
     */
    public static void rmuser() {
        remove(USER_SAVE_NAME);
    }


}
