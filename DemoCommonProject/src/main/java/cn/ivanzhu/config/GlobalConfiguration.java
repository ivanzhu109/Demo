package cn.ivanzhu.config;

import cn.ivanzhu.model.TestModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ivanzhu
 * @date 2020/7/6
 * @time 13:56
 */
@Configuration
public class GlobalConfiguration {

    @Bean
    public TestModel getTestModel(){
        return new TestModel();
    }
}
