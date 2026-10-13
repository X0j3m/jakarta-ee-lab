package com.x0j3m.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ImageRepository {
    private final Path imageStoragePath;

    public ImageRepository(String imageStoragePathString) {
        imageStoragePath = Path.of(imageStoragePathString);
        try {
            if (!Files.exists(imageStoragePath)) {
                Files.createDirectories(imageStoragePath);
                System.out.println("Images directory created: " + imageStoragePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot create images directory: " + imageStoragePath + " . Error: " + e);
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
