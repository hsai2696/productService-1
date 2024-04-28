package com.productservice.products.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProductCreateRequestDto {


    String title;
    Double price;
    String email;
}
