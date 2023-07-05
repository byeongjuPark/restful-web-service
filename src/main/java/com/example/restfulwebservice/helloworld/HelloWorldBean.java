package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok
// @Data HelloWorldBean 클래스의 getter, setter, toString 제공
// @AllArgsConstructor 생성자 제공
// @NoArgsConstructor 기본 생성자 제공
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;
}
