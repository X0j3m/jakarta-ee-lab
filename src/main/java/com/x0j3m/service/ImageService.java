package com.x0j3m.service;

import com.x0j3m.data.ImageRepository;

import java.io.IOException;
import java.io.InputStream;

public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

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
