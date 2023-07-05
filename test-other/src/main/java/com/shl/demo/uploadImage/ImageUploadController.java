package com.shl.demo.uploadImage;

import cn.hutool.core.codec.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ImageUploadController {

    @Resource
    private ImageUploadService imageUploadService;
    @Resource
    private ImageMapper imageMapper;


    /**
     * 上传图片保存至文件中
     *
     * @param file file
     * @return String
     */
    @PostMapping("/api/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (imageUploadService.isValidImage(file)) {
                String fileName = imageUploadService.saveImage(file);
                return "上传成功，文件名为：" + fileName;
            } else {
                return "上传失败，文件格式不正确或文件大小超出限制";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败，" + e.getMessage();
        }
    }

    /**
     * 上传图片校验并返回base64字符创
     *
     * @param file file
     * @return String
     */
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (!imageUploadService.isValidImage(file)) {
                return "上传失败，文件格式不正确或文件大小超出限制";
            }
            byte[] imageData = file.getBytes();
            String encode = Base64.encode(imageData);
            Image image = new Image();
            image.setName("name");
            image.setData(imageData);
            imageMapper.insert(image);
            return encode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验图片base64字符串
     *
     * @param base64Image base64Image
     * @return boolean
     */
    @PostMapping("/validate")
    public boolean getImage(@RequestParam("base64Image") String base64Image) {
        return imageUploadService.validate(base64Image);
    }


    public static void main(String[] args) {
        // 将图片文件转化为16进制返回
        String imagePath = "C:\\Users\\Lenovo\\Desktop\\3.png";
        try {
            byte[] imageData = Files.readAllBytes(Paths.get(imagePath));
            StringBuilder sb = new StringBuilder();
            for (byte b : imageData) {
                sb.append(String.format("%02X", b));
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}