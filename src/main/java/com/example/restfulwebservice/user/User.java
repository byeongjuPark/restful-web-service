package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
// @JsonIgnoreProperties(value={"password", "ssn"})
// @JsonFilter("UserInfo") ->  FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); 에서 사용
@NoArgsConstructor
@JsonFilter("UserInfo")
public class User {
    private Integer id;
    
    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;

    // @JsonIgnore 클라이언트가 받을 때 넘어가지 않음
    // @JsonIgnoreProperties 클래스 단위로 관리 할 수 있음.
    private String password;
    private String ssn;
}
