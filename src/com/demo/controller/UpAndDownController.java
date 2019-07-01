package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.demo.common.AjaxResUtil;
import com.demo.common.AjaxResponse;

@Controller
public class UpAndDownController {
    @Value("${web.upload-path}")
    private  String uploadPath;

    public String getNewFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String nName = UUID.randomUUID().toString().replaceAll("-", "");
        return nName + "." + suffix;
    }

    /**
     * 实现文件上传
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return AjaxResUtil.createError("file文件参数为空", "E1001");
            }
            String fileName = getNewFileName(file);
            String filePath = uploadPath + fileName;
            File dest = new File(filePath);
            if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest); // 保存文件
            return AjaxResUtil.createSuccess(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResUtil.createError(e.getMessage(), "E1000");
        }
    }

    private final ResourceLoader resourceLoader;

    @Autowired
    public UpAndDownController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 显示单张图片
     * 
     * @return
     */
    @RequestMapping("visit/{fileName:.+}")
    public ResponseEntity visit(@PathVariable("fileName")String fileName) {
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + uploadPath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
