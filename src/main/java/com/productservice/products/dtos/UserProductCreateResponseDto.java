package com.productservice.products.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProductCreateResponseDto extends UserProductCreateRequestDto{

    String errorMessage;
}