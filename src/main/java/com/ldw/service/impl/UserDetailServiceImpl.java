package com.ldw.service.impl;

import com.ldw.entity.Role;
import com.ldw.entity.User;
import com.ldw.service.RoleService;
import com.ldw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 校验认证方法
 * @author HP刘德伟
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 完成账号的校验
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        if (user != null) {
            Role role=roleService.selectByUserRoleId(user.getRoleId());
            List<GrantedAuthority> listRole = new ArrayList<>();
            listRole.add(new SimpleGrantedAuthority(role.getName()));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User
                    (user.getUsername(),user.getPassword(),listRole);
            return userDetails;
        }
        return null;
    }
}
