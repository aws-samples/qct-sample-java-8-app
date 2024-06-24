package com.bhoruka.bloodbank.model.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetCampRequest {

    private String campId;
}
