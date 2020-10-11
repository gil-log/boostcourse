package com.noryangjin.boostcourse.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseVO {

    private HttpStatus code;
    private String message;
    private Object data;

}
