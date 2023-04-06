package com.shl.demo.uploadImage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Objects;

public class ImageUploadService {

    private static final int MAX_SIZE = 2 * 1024 * 1024; // 最大2MB
    private static final int WIDTH_LIMIT = 320;
    private static final int HEIGHT_LIMIT = 320;
    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png"};

    public boolean isValidImage(MultipartFile file) throws IOException {
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

        return file.getSize() <= MAX_SIZE && image.getWidth() <= WIDTH_LIMIT && image.getHeight() <= HEIGHT_LIMIT;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    private boolean isAllowedExtension(String extension) {
        for (String ext : ALLOWED_EXTENSIONS) {
            if (ext.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    public String saveImage(MultipartFile file) throws IOException {
        String extension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String newFileName = System.currentTimeMillis() + "." + extension;
        String savePath = "/path/to/save/images/" + newFileName; // 绝对路径
        File dest = new File(savePath);
        File parentFile = dest.getParentFile();
        if (!parentFile.exists()) {
            boolean success = false;
            try {
                success = parentFile.mkdirs();
            } catch (SecurityException e) {
                // 处理异常
                e.printStackTrace();
            }
            if (!success) {
                // 创建目录失败，处理逻辑
                System.out.println("抛出异常");
            }
        }
        file.transferTo(dest);
        return newFileName;
    }


    public boolean validate(String base64Image) {
        if (StringUtils.isEmpty(base64Image)) {
            return false;
        }

        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        if (imageBytes.length > MAX_SIZE) {
            return false;
        }

        try (ByteArrayInputStream in = new ByteArrayInputStream(imageBytes)) {
            ImageInputStream iis = ImageIO.createImageInputStream(in);
//            getFormatName()是BufferedImage类中的方法，但是需要注意的是该方法在Java SE 9中被弃用，如果您使用的是Java SE 9及以上版本，则建议使用ImageReader类来获取图像格式
//            String formatName = ImageIO.read(in).getFormatName().toLowerCase();
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                return false;
            }
            ImageReader reader = readers.next();
            String formatName = reader.getFormatName().toLowerCase();
            return formatName.equals("jpg") || formatName.equals("jpeg") || formatName.equals("png");
        } catch (IOException e) {
            return false;
        }
    }
}
