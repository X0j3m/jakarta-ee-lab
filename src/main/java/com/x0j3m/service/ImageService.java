package com.x0j3m.service;

import com.x0j3m.data.ImageRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
public class ImageService {
    @Inject
    private ImageRepository imageRepository;

    public byte[] fetchImage(String imageName) {
        return imageRepository.getImage(imageName).orElse(new byte[0]);
    }

    public void createImage(String imageName, InputStream data) throws IOException {
        try {
            imageRepository.saveImage(imageName, data.readAllBytes());
        }catch (IOException ignored){
        }
    }

    public void deleteImage(String imageName) {
        try {
            imageRepository.deleteImage(imageName);
        }catch (IOException ignored){
        }
    }
}
