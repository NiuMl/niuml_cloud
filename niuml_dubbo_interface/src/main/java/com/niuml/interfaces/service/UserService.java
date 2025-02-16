package com.niuml.interfaces.service;

import com.niuml.interfaces.entity.User;

/***
 * User:niumengliang
 * Date:2025/2/13
 * Time:17:09
 */
public interface UserService {

    void login(String username);

    void save(User user);

    User getUserById(int id);
}
