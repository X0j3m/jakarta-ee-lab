package com.x0j3m.config;

import com.x0j3m.data.UserRepository;
import com.x0j3m.model.mapper.UserMapper;
import com.x0j3m.service.UserService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class UserServiceContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserRepository repository = (UserRepository) sce.getServletContext().getAttribute(UserRepository.class.getName());
        UserMapper userMapper = (UserMapper) sce.getServletContext().getAttribute(UserMapper.class.getName());
        UserService service = new UserService(repository, userMapper);
        sce.getServletContext().setAttribute(UserService.class.getName(), service);
    }
}
