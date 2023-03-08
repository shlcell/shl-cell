package com.shl.demo.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * jdbc连接 测试控制器
 */
@RestController
public class TestJdbcController {

    @GetMapping("/test")
    public Map<Object,Object>  getSqlInfo() throws ClassNotFoundException, SQLException {
        Map<Object,Object> map = new HashMap<>();
        //1. 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 创建连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.91.129:3306/test_jdbc?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "root");
        //3.编写sql
        String sql = "select * from student";
        //4.执行sql使用PreparedStatement对象
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        //可以进行设置参数 防止sql注入
        //5.获取结果
        ResultSet rs = prepareStatement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            map.put("id", id);
            map.put("name", name);
        }
        //6.关闭资源
        rs.close();
        prepareStatement.close();
        connection.close();
        return map;
    }
}
