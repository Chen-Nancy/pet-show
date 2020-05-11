package com.nancy.petshow.entity;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author chen
 * @date 2020/5/11 16:28
 */
@Data
@ToString
public class User {
    @NotBlank
    private String name;
    private Integer age;
}
