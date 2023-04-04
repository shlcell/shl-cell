package com.shl.demo.apitest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ApiInfoDao {

    void save(ApiEntity api);

    List<ApiEntity> getAllApis();
}


