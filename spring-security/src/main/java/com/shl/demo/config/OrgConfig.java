package com.shl.demo.config;

import com.shl.demo.domain.OrgInfo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "org-info")
public class OrgConfig {

    private List<Map<String, String>> orgInfos;
}
