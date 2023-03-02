package com.shl.kafka.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {
    // 评论ID
    private Integer id;

    // 课程id
    private Integer courseId;

    // 讲师id
    private Integer teacherId;

    // 会员id
    private Integer memberId;

    // 会员昵称
    private String nickname;

    // 评论内容
    private String content;
}



    
