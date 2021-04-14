package org.lbq.wrsboot.service;

import org.lbq.wrsboot.bean.Hr;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
*
*/
public interface HrService {

    Hr getHrRoleByUserName(String username);
}
