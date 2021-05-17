package wtu.studentmanage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wtu.studentmanage.message.Page;
import wtu.studentmanage.message.RestMsg;
import wtu.studentmanage.pojo.Student;
import wtu.studentmanage.servcer.StudentService;


/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-9:34
 */
@RestController
@RequestMapping("/api/v1/student")
public class StudentApi {

    @Autowired
    private StudentService service;

    @GetMapping
    public RestMsg<Page<Student>> list(String key, Integer page, Integer size) {
        try {
            return new RestMsg<>(service.list(key, page, size));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @PostMapping
    public RestMsg<Student> add(Student student) {
        try {
            return new RestMsg<>(service.add(student));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @DeleteMapping
    public RestMsg<Student> del(int id) {
        try {
            return new RestMsg<>(service.del(id));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }

    @PutMapping
    public RestMsg<Student> modify(Student student) {
        try {
            return new RestMsg<>(service.update(student));
        } catch (Exception e) {
            return new RestMsg<>(e);
        }
    }
}
