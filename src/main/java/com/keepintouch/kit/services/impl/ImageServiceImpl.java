package com.keepintouch.kit.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.keepintouch.kit.helpers.AppConstants;
import com.keepintouch.kit.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile image, String filename) {
        try {
            byte[] imageBytes = new byte[image.getInputStream().available()];
            image.getInputStream().read(imageBytes);
            cloudinary.uploader().upload(imageBytes, ObjectUtils.asMap(
                    "public_id", filename
            ));
            return getImageUrl(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getImageUrl(String publicId) {
        return cloudinary.url()
                .transformation(new Transformation<>()
                        .width(AppConstants.CONTACT_IMAGE_WIDTH)
                        .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                        .crop(AppConstants.CONTACT_IMAGE_CROP)
                )
                .generate(publicId);
    }


}
