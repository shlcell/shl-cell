package com.shl.demo.contorller;

import com.shl.demo.service.ImageUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageUploadController {

    @PostMapping("/api/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (ImageUploadService.isValidImage(file)) {
                String fileName = ImageUploadService.saveImage(file);
                return "上传成功，文件名为：" + fileName;
            } else {
                return "上传失败，文件格式不正确或文件大小超出限制";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败，" + e.getMessage();
        }
    }
}