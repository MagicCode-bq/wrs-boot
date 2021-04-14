package org.lbq.wrsboot.service.impl;


import org.lbq.wrsboot.bean.Hr;
import org.lbq.wrsboot.service.HrService;
import org.lbq.wrsboot.mapper.HrMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
*
*/
@Service
public class HrServiceImpl  implements HrService{


    @Resource
    HrMapper hrMapper;

    @Override
    public Hr getHrRoleByUserName(String username) {
       return hrMapper.getHrRoleByUserName(username);
    }
}
