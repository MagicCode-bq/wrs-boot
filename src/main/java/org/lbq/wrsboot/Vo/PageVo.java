package org.lbq.wrsboot.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo implements Serializable {
    private  Integer pages;
    private List<Object> PageList;
}
