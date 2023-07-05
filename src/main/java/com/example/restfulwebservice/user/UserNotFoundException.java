package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status code
// 2XX -> OK
// 4XX -> Client side
// 5XX -> Server side

// HttpStatus 이 예외 클래스는 NOT_FOUND 오류로 발생시킴
// 원래는 5XX 이나 404로 오류 출력
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }

}
