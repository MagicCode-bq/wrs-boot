package org.lbq.wrsboot.mapper;

import org.lbq.wrsboot.bean.Menu;

import java.util.List;

/**
* @Entity org.lbq.wrsboot.bean.Menu
*/
public interface MenuMapper {

    List<Menu> gteHrMenus(Integer hrid);
}
