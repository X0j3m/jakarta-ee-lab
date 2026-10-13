package com.x0j3m.config.repository;

import com.x0j3m.data.ImageRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.nio.file.Path;

@WebListener
public class ImageRepositoryContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String imgResDirectory = context.getInitParameter("image.directory");
//        String configuredPath = System.getProperty("java.io.tmpdir") + "/images";
        String configuredPath=Path.of(imgResDirectory).toAbsolutePath().normalize().toString();
        ImageRepository repository = new ImageRepository(configuredPath);
        sce.getServletContext().setAttribute(ImageRepository.class.getName(), repository);
    }
}
