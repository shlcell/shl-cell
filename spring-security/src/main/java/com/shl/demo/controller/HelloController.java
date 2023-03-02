package com.shl.demo.controller;

import com.shl.demo.config.OrgConfig;
import com.shl.demo.domain.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("@ex.hasAuthority('select')")
//    @OpLog(title = "hello")
//    @PreAuthorize("hasAuthority('select')")
//    @PreAuthorize("hasAnyAuthority('admin','test','system:dept:list')")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAnyRole('admin','system:dept:list')")
    public ResponseResult hello() {
//        int i=1/0;
        //获取ftpInfo对象的数组
        List<Map<String, String>> orgInfos = orgConfig.getOrgInfos();

        if (orgInfos.size() > 0) {
            //遍历list集合，取出多个ftp配置
            for (Map<String, String> orgInfo : orgInfos) {
                System.out.println("机构编码：" + orgInfo.get("orgCode"));
                System.out.println("ip地址：" + orgInfo.get("ip"));
            }
        }
        return new ResponseResult(200, "hello");
    }


    @Autowired
    private OrgConfig orgConfig;

    public void test() {
        List<Map<String, String>> orgInfos = orgConfig.getOrgInfos();

        if (orgInfos.size() > 0) {
            //遍历list集合，取出多个ftp配置
            for (Map<String, String> orgInfo : orgInfos) {
                System.out.println("机构编码：" + orgInfo.get("orgCode"));
                System.out.println("ip地址：" + orgInfo.get("ip"));
            }
        }
    }
//    @Transactional
//    public void create(User user){
//        // 如果用户已存在，则先删除
//        delete(user.id);
//        // 创建用户
//        int userId = insert(user);
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//            @Override
//            public void afterCommit() {
//                //  更新用户信息
//                update(userId);
//            }
//        });
//    }
//
//    @Async
//    public void update(Integer userId){
//        Icon icon = getUserIcon(userId);
//        // 更新用户图片
//        updateUserPohot(userId,icon);
//    }

}