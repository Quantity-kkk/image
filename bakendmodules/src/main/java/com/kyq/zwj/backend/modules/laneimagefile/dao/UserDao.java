package com.kyq.zwj.backend.modules.laneimagefile.dao;

import com.kyq.zwj.backend.entity.User;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 14:23
 */
public interface UserDao {
    public User getUser(User user);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int UserId);
}
