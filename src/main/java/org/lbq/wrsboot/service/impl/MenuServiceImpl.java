package org.lbq.wrsboot.service.impl;


import org.lbq.wrsboot.bean.Menu;
import org.lbq.wrsboot.service.MenuService;
import org.lbq.wrsboot.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
*/
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenus(Integer hrId) {
       return  menuMapper.gteHrMenus(hrId);
    }
}
