package com.shl.test;

import com.shl.demo.design.HandleConfig;
import com.shl.demo.vo.BlackInfo;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ReportTest {

    @Resource
    private HandleConfig handleConfig;

    @Resource
    private BlackInfo blackInfo;

    @Test
    public void configDemo() {
//        List<String> sqlBlacklist = blackInfo.getSqlBlacklist();
        List<String> handler1 = handleConfig.getHandler1();
    }
}
