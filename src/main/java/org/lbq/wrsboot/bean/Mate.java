package org.lbq.wrsboot.bean;

import lombok.Data;

@Data
public class Mate {
    /**
     * 是否保存组件状态
     */
    private Boolean keepalive;

    /**
     * 是否需要登陆访问
     */
    private Boolean requireauth;
}
