package com.ecommerce.api.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionDTO {
    private String type;
    private String title;
    private String detail;
}
