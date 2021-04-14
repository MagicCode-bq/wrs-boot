package org.lbq.wrsboot.service;

import org.lbq.wrsboot.bean.Menu;

import java.util.List;

/**
*
*/
public interface MenuService {

    //查询用户角色的菜单
    List<Menu> getMenus(Integer hrId);

}
