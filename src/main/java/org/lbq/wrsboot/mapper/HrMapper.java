package org.lbq.wrsboot.mapper;


import org.lbq.wrsboot.bean.Hr;

/**
* @Entity org.lbq.wrsboot.bean.Hr
*/
public interface HrMapper  {

    //查询用户名对应用户以及角色信息
    Hr getHrRoleByUserName(String username);

}
