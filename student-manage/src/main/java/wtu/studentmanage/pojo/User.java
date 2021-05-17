package wtu.studentmanage.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    /**
     * 名字
     */
    private String name;

    private String department;

    private String office;

    private String phone;

    private String QQ;

    private static final long serialVersionUID = 1L;
}