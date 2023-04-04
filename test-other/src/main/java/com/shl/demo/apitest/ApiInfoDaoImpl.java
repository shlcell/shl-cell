package com.shl.demo.apitest;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApiInfoDaoImpl implements ApiInfoDao {

//    @Autowired
//    private DataSource dataSource;

    @Override
    public void save(ApiEntity api) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.update("INSERT INTO api_info (url, method) VALUES (?, ?)", api.getUrl(), api.getMethod());
    }

    @Override
    public List<ApiEntity> getAllApis() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        List<ApiEntity> apis = jdbcTemplate.query("SELECT * FROM api_info", new BeanPropertyRowMapper<>(ApiEntity.class));
//        return apis;
        return null;
    }
}