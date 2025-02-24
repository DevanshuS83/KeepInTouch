package com.keepintouch.kit.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file, String filename);
    String getImageUrl(String publicId);
}
