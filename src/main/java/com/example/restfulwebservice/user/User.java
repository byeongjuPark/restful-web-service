package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@AllArgsConstructor
// @JsonIgnoreProperties(value={"password", "ssn"})
// @JsonFilter("UserInfo") ->  FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); 에서 사용
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
// @Entity 테이블 지정 및 테이블 생성
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    @Past
    @ApiModelProperty(notes = "등록일을 입력해 주세요.")
    private Date joinDate;

    // @JsonIgnore 클라이언트가 받을 때 넘어가지 않음
    // @JsonIgnoreProperties 클래스 단위로 관리 할 수 있음.
    @ApiModelProperty(notes = "사용자 패스워드을 입력해 주세요.")
    private String password;
    @ApiModelProperty(notes = "사용자 주민번호을 입력해 주세요.")
    private String ssn;
}
