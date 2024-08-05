package com.shl.demo.service.impl;

import com.shl.demo.entity.Student;
import com.shl.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> createD() {
        int a = 10;
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            Student student = new Student();
            student.setId(i)
                    .setMemberId(i)
                    .setCourseId(i)
                    .setTeacherId(i)
                    .setNickname("发发发发发发发发发" + i)
                    .setContent("测试压缩" + i);
            list.add(student);
        }
        return list;
    }
}
