package com.shl.demo.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取 nacos 配置文件中blacklist为前缀的list集合
 */
@Data
@ConfigurationProperties(prefix = "blacklist")
@Component
public class BlackInfo {
    private List<String> sqlBlacklist;
    private List<String> databaseBlackList;

}
