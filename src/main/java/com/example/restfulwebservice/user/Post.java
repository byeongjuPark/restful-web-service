package com.example.restfulwebservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // jpa 
@Data // setter, getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    // @GeneratedValue 그냥 쓰면 엔티티별로 id가 분리되지 않고 함께 증가
    // strategy = GenerationType.IDENTITY 
    private Integer id;

    private String description;

    // User : Post -> 1 : (0~N)
    // jpa 관계 차수를 표시하기 위해서는 메인/서브를 명시해주어야 함.
    // Main : Sub -> Parent -> : Child
    @ManyToOne(fetch = FetchType.LAZY) 
    // FetchType.Lazy = 지연 로딩 방식. 매번 같이 로딩하는게 아니라, Post데이터가 로딩되는 시점에 필요한 사용자 데이터를 가져온다.
    // Post가 여러개가 올 수 있고 하나의 값과 매칭 될 수 있다.(POST입장에서 보았을 때)
    @JsonIgnore // 이 값은 반출되지 않는다.
    private User user;
    
}
