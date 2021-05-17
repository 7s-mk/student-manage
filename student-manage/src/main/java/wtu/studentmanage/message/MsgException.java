package wtu.studentmanage.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 描述：消息
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-9:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MsgException extends RuntimeException {
    private String msg;
    private LocalDateTime dateTime;

    public MsgException(String msg) {
        this.msg = msg;
        dateTime = LocalDateTime.now();
    }
}
