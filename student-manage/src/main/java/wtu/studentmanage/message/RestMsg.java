package wtu.studentmanage.message;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-9:17
 */
@Data
public class RestMsg<T> {
    /**
     * 是否请求成功
     */
    private boolean success;
    /**
     * 实际请求结果
     */
    private T rest;
    /**
     * 失败后的消息
     */
    private String msg;
    /**
     * 请求发起时间
     */
    private LocalDateTime time;

    public RestMsg() {
        this.success = true;
        this.time = LocalDateTime.now();
    }

    public RestMsg(T rest) {
        this();
        this.success = true;
        this.rest = rest;
    }

    public RestMsg(T rest, String msg) {
        this(rest);
        this.msg = msg;
        this.success = false;
    }

    public RestMsg(Exception e) {
        this(null, "");
        if (e != null) {
            e.printStackTrace();
        }
        if (e instanceof MsgException) {
            this.msg = ((MsgException) e).getMsg();
        } else {
            this.msg = "请求失败，服务发生错误";
        }
    }
}
