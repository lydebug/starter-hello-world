package com.example.demo.service;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @Author: liyang27
 * @Date: 2020/8/11 12:03
 * @Description:
 */
@Data
//@Service
public class HelloworldService {
    private String words;
    public String sayHello(){
        return "hello "+words;
    }
}
