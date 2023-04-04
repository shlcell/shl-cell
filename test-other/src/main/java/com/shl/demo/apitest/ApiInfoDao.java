package com.shl.demo.apitest;

import java.util.List;

public interface ApiInfoDao {

    void save(ApiEntity api);

    List<ApiEntity> getAllApis();
}


