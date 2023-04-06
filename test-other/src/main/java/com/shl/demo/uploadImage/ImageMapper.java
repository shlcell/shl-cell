package com.shl.demo.uploadImage;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    void insert(Image image);
}
