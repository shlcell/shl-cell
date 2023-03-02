package com.shl.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shl.demo.domain.User;
import com.shl.demo.mapper.UserMapper;
import com.shl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @PostMapping("/test")
    public void test() {
        User user = new User();
        user.setUserName("22222更新");
        user.setNickName("222222");
        user.setEmail("22222222@qq.com");

        User user1 = new User();
        user1.setUserName("3333");
        user1.setNickName("3333");
        user1.setEmail("33333333333@qq.com");
//        userMapper.insert(user);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lt("id",100);
        ArrayList<User> arrayList = new ArrayList<>();
        List<User> userList = userMapper.selectList(userQueryWrapper);
        userList.add(user);
        userList.add(user1);
        int i = userMapper.saveOrUpdateBatch(userList);
        System.out.println(i);
//        userMapper.saveOrUpdate(user);
//        userService.saveOrUpdate(user);
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("status", 0);
//        userQueryWrapper.select("user_name");
//        List<User> usersList = userMapper.selectList(userQueryWrapper);
//        ArrayList<UserVO> arrayList = new ArrayList<>();
//        ArrayList<User> arrayList1 = new ArrayList<>();
//        for (User user1 : usersList) {
//            UserVO userVO = new UserVO();
//            userVO.setUserName(user1.getUserName());
//            arrayList.add(userVO);
//            System.out.println(user1);
//        }
//        userService.saveOrUpdateBatch(arrayList1);
//        userService.updateBatchById();
//        userService.saveOrUpdateBatch();
    }
}
