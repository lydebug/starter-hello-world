package com.example.demo.conf;

import com.example.demo.prop.HelloworldProp;
import com.example.demo.service.HelloworldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liyang27
 * @Date: 2020/8/11 13:50
 * @Description:
 */
@Configuration
//当HelloworldService在类路径下
@ConditionalOnClass({HelloworldService.class})
//将application.properties的相关属性字段与该类一一对应，并生成bean
@EnableConfigurationProperties(HelloworldProp.class)
public class HelloworldAutoConfiguration {

    @Autowired
    private HelloworldProp helloworldProp;

    @Bean
    //当容器没有这个Bean的时候才创建这个Bean
    @ConditionalOnMissingBean(HelloworldService.class)
    public HelloworldService helloworldService(){
        HelloworldService helloworldService = new HelloworldService();
        helloworldService.setWords(helloworldProp.getWords());
        return helloworldService;
    }
}
