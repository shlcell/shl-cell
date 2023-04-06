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
        // 不是有效的图像
        if (image == null) {
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

/*
try-with-resources语句是 Java 7 引入的一种新的流程控制结构。它可以让开发者更加方便地管理资源，并且使代码更加简洁易读。
在传统的try-catch-finally结构中，我们需要显式地关闭资源，如文件、数据库连接、网络连接等。
而在使用try-with-resources结构时，只需要在try关键字后面的括号中声明要使用的资源，Java 会自动在代码块执行完毕后关闭这些资源，从而避免了繁琐的释放资源操作。

ImageInputStream和BufferedImage都是Java图像处理领域中的常用类，但是它们的功能和应用场景有所不同。

ImageInputStream是一个可寻址的输入流接口，用于读取图片数据以及元数据。
它继承自Java标准库中的DataInput接口和Closeable接口，可支持基本的数据操作和关闭资源等操作。
很多图像处理库和工具使用ImageInputStream读取图片数据并解码成图像对象，例如Java自带的ImageIO类就使用了ImageInputStream来读取和处理图片文件。

BufferedImage是一个具体的图像缓冲区类，它描述了一个包含图像数据的可访问缓冲区。
一个BufferedImage对象由图像的颜色模型和像素数据组成，它可以更加灵活地操作图片数据、生成新的图像、裁剪和缩放等常见图像处理操作。
在日常的Java图像处理开发中，我们通常会将BufferedImage作为图像对象进行创建、修改和保存等操作，例如使用Graphics2D类在BufferedImage上绘制图形或者转换图片格式等。

因此， ImageInputStream是一个读取和解码图片数据的工具类，而BufferedImage则是一个用于描述和处理图像缓冲区的类，它们在图像处理中有着不同的作用和应用场景。
*/
        try (ByteArrayInputStream in = new ByteArrayInputStream(imageBytes)) {
            BufferedImage image = ImageIO.read(in);
            int width = image.getWidth();
            int height = image.getHeight();
            if (width > WIDTH_LIMIT || height > HEIGHT_LIMIT) {
                return false;
            }
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
