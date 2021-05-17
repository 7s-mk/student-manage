package wtu.studentmanage.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * stu_student
 * @author 
 */
@Data
public class Student implements Serializable {
    private Integer id;

    private String cid;

    private Integer classes;

    private String name;

    private String gender;

    private Integer age;

    private String tel;

    private String email;

    private static final long serialVersionUID = 1L;
}