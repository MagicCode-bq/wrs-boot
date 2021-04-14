package org.lbq.wrsboot.controller.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lbq.wrsboot.bean.Hr;
import org.lbq.wrsboot.bean.Menu;
import org.lbq.wrsboot.service.impl.MenuServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class menuController {

    @Resource
    MenuServiceImpl menuServiceimpl;

    @GetMapping("/menu")
    public String getMenu() throws JsonProcessingException {
     //获取当前系统中登陆用户
        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

     //查询当前用户拥有的角色对应的菜单
        List<Menu> menus = menuServiceimpl.getMenus(hr.getId());

        String s = new ObjectMapper().writeValueAsString(menus);
        return s;
    }
}
