package wtu.studentmanage.servcer;

import wtu.studentmanage.message.Page;
import wtu.studentmanage.pojo.Student;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-9:39
 */
public interface StudentService {
    Page<Student> list();

    Page<Student> list(String key);

    Page<Student> list(String key, Integer page);

    Page<Student> list(String key, Integer page, Integer size);

    Student add(Student student);

    Student del(int id);

    Student update(Student student);


}
