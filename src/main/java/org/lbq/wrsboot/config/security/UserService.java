package org.lbq.wrsboot.config.security;

import org.lbq.wrsboot.bean.Hr;
import org.lbq.wrsboot.mapper.HrMapper;
import org.lbq.wrsboot.service.impl.HrServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Component
public class UserService implements UserDetailsService {

   @Resource
    HrServiceImpl hrServiceimpl;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询当前登陆用户的信息
        Hr hr = hrServiceimpl.getHrRoleByUserName(username);
          if(ObjectUtils.isEmpty(hr)){
              throw   new UsernameNotFoundException("当前用户不存在，请重新输入");
          }
        return hr;
    }
}
