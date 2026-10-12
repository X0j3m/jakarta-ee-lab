package com.x0j3m.config;

import com.x0j3m.model.mapper.UserMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class UserMapperContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserMapper userMapper = new UserMapper();
        sce.getServletContext().setAttribute(UserMapper.class.getName(), userMapper);
    }
}
