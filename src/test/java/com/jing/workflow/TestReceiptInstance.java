package com.jing.workflow;

import com.jing.workflow.service.ProcessTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/21 15:00
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReceiptInstance {

    @Resource
    private ProcessTaskService taskService;

    @Test
    public void test() {

    }

}
