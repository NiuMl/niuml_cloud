package com.niuml.auth.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/***
 * @author niumengliang
 * Date:2025/1/23
 * Time:12:24
 */
@Data
public class Input {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, message = "用户名至少为6位")
    private String userName;
    private String password;
}
