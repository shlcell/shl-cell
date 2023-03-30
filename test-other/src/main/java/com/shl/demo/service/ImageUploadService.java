package com.shl.demo.service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUploadService {

    private static final int MAX_SIZE = 2 * 1024 * 1024; // 最大2MB
    private static final int WIDTH_LIMIT = 320;
    private static final int HEIGHT_LIMIT = 320;
    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png"};

    public static boolean isValidImage(MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            return false;
        }

        String extension = getFileExtension(file.getOriginalFilename());
        if (!isAllowedExtension(extension)) {
            return false;
        }

        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image == null) { // 不是有效的图像
            return false;
        }

        if (file.getSize() > MAX_SIZE || image.getWidth() > WIDTH_LIMIT || image.getHeight() > HEIGHT_LIMIT) {
            return false;
        }

        return true;
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    private static boolean isAllowedExtension(String extension) {
        for (String ext : ALLOWED_EXTENSIONS) {
            if (ext.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    public static String saveImage(MultipartFile file) throws IOException {
        String extension = getFileExtension(file.getOriginalFilename());
        String newFileName = System.currentTimeMillis() + "." + extension;
        String savePath = "/path/to/save/images/" + newFileName; // 绝对路径
        File dest = new File(savePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return newFileName;
    }
}
