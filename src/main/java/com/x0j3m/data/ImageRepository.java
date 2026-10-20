package com.x0j3m.data;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@ApplicationScoped
public class ImageRepository {

    private static final String STORAGE_PATH_CONFIG = "C:\\temp\\images";

    private Path imageStoragePath;

    @Inject
    public ImageRepository() {
    }

    @PostConstruct
    public void init() {
        this.imageStoragePath = Path.of(STORAGE_PATH_CONFIG);
        try {
            if (!Files.exists(imageStoragePath)) {
                Files.createDirectories(imageStoragePath);
                System.out.println("Images directory created: " + imageStoragePath.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot create images directory: " + imageStoragePath + ". Error: " + e, e);
        }
    }

    public Optional<byte[]> getImage(String imageName) {
        Path imagePath = imageStoragePath.resolve(imageName);
        try {
            return Optional.of(Files.readAllBytes(imagePath));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public void saveImage(String imageName, byte[] imageBytes) throws IOException {
        Path imagePath = imageStoragePath.resolve(imageName);
        try {
            Files.write(imagePath, imageBytes);
        } catch (IOException e) {
            throw new IOException("Cannot save image: " + imageName);
        }
    }

    public void deleteImage(String imageName) throws IOException {
        Path imagePath = imageStoragePath.resolve(imageName);
        try {
            Files.delete(imagePath);
        } catch (IOException e) {
            throw new IOException("Cannot delete image: " + imageName);
        }
    }
}
