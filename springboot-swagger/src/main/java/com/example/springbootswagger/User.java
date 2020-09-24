package com.example.springbootswagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: xuruifeng
 * @Date: 2020/9/24 17:17
 */
@Data
@ApiModel(description = "用户实体")  //ApiModel表示实体模型
public class User {

    @ApiModelProperty(value = "用户编号")    //ApiModelProperty表示模型属性
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户年龄")
    private Integer age;
}
