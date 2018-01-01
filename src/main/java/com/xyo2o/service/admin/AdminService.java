package com.xyo2o.service.admin;

import com.xyo2o.domain.admin.Admin;

public interface AdminService {
    Admin selectOneByUsername(String username);

    int insert(Admin admin);
}
