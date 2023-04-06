package com.shl.demo.uploadImage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.BlobTypeHandler;

@Data
@TableName("images")
public class Image {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField(typeHandler = BlobTypeHandler.class)
    private byte[] data;
}
