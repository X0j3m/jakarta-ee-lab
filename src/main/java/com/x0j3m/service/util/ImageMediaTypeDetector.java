package com.x0j3m.service.util;

import java.util.Arrays;

public class ImageMediaTypeDetector {
    private static final String IMAGE_PNG = "image/png";
    private static final String IMAGE_JPEG = "image/jpeg";

    private static final byte[] PNG_SIGNATURE = {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};

    private static final byte[] JPEG_SIGNATURE = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};

    private ImageMediaTypeDetector() {
    }

    public static String detect(byte[] image) {
        if (matchesSignature(image, PNG_SIGNATURE)) {
            return IMAGE_PNG;
        }
        if (matchesSignature(image, JPEG_SIGNATURE)) {
            return IMAGE_JPEG;
        }
        throw new IllegalStateException("Unrecognized image format");
    }

    private static boolean matchesSignature(byte[] data, byte[] signature) {
        return data.length >= signature.length
                && Arrays.equals(data, 0, signature.length, signature, 0, signature.length);
    }

}
