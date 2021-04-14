package org.lbq.wrsboot.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespVo {
    private Integer status;
    private String  message;
    private Object Obj;
}
