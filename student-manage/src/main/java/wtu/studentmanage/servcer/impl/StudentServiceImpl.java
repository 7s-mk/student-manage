package wtu.studentmanage.servcer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wtu.studentmanage.dao.StudentDao;
import wtu.studentmanage.message.MsgException;
import wtu.studentmanage.message.Page;
import wtu.studentmanage.pojo.Student;
import wtu.studentmanage.pojo.StudentExample;
import wtu.studentmanage.servcer.StudentService;

import java.util.List;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-9:41
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao dao;

    @Override
    public Page<Student> list() {
        List<Student> students = dao.selectByExample(null);
        return Page.rest(students, null, 1, students.size(), students.size());
    }

    @Override
    public Page<Student> list(String key) {
        return list(key, null, null);
    }

    @Override
    public Page<Student> list(String key, Integer page) {
        return list(key, page, 20);
    }

    @Override
    public Page<Student> list(String key, Integer page, Integer size) {
        StudentExample example = new StudentExample();

        key = key == null ? "" : key;
        key = "%" + key + "%";
        example.createCriteria().andNameLike(key);
        example.or().andTelLike(key);
        long count = dao.countByExample(example);

        if (page != null && size != null) {
            size = size < 1 ? 10 : size;
            example.setLimit(size);
            page = Math.max(page, 1);
            example.setOffset((long) (size * (page - 1)));
        }
        return Page.rest(dao.selectByExample(example), key, page, size, count);
    }

    @Override
    public Student add(Student student) {
        StudentExample example = new StudentExample();
        example.createCriteria().andCidEqualTo(student.getCid());
        if (dao.countByExample(example) > 0) {
            throw new MsgException("学号重复");
        }
        dao.insertSelective(student);
        return student;
    }

    @Override
    public Student del(int id) {
        Student student = dao.selectByPrimaryKey(id);
        if (student == null) {
            throw new MsgException("该学生不存在");
        }

        dao.deleteByPrimaryKey(id);

        return student;
    }

    @Override
    public Student update(Student student) {
        Student stu = dao.selectByPrimaryKey(student.getId());
        if (stu == null) {
            throw new MsgException("该学生不存在");
        }
        dao.updateByPrimaryKeySelective(student);
        return dao.selectByPrimaryKey(student.getId());
    }
}
