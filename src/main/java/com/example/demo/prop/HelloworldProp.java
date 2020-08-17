package com.example.demo.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: liyang27
 * @Date: 2020/8/11 12:05
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "helloworld")
//@Component
public class HelloworldProp {
    public static final String DEFAULT_WORDS="world";

    private String words=DEFAULT_WORDS;

}
