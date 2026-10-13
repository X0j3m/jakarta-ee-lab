package com.x0j3m.config.service;

import com.x0j3m.data.ImageRepository;
import com.x0j3m.service.ImageService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ImageServiceContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ImageRepository repository = (ImageRepository) sce.getServletContext().getAttribute(ImageRepository.class.getName());
        ImageService service = new ImageService(repository);
        sce.getServletContext().setAttribute(ImageService.class.getName(), service);
    }
}
