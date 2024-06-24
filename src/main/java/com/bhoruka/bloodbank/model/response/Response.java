package com.bhoruka.bloodbank.model.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Response<T> {

    T data;

    Integer status;

    String description;

    String errorMessage;
}
