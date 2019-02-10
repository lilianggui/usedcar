package com.llg.usedcar.utils;

import com.llg.usedcar.entity.CarImage;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: Lange
 * @Date: 2019/1/17 23:26
 * @Description:
 */
public class UploadUtils {

    public static String uploadFile(MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        //String filePath = "C:/doc/";
        String filePath = request.getServletContext().getRealPath("/");
        Date now = new Date();
        File dateFile = new File(filePath + CommonConst.SF.format(now));
        if(!dateFile.exists()){
            dateFile.mkdirs();
        }
        String saveFileName = UUID.randomUUID().toString();
        if(!StringUtils.isEmpty(fileName)){
            saveFileName += fileName.substring(fileName.lastIndexOf("."));
        }
        File dest = new File(dateFile , saveFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getAbsolutePath().replaceAll("\\\\","/");
    }

    public static String saveTempFile(MultipartFile file) throws IOException {
        String saveFileName = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        if(!StringUtils.isEmpty(fileName)){
            saveFileName += fileName.substring(fileName.lastIndexOf("."));
        }
        File outputFile = new File(getFileSavePath("temp"), saveFileName);
        try {
            file.transferTo(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath().replaceAll("\\\\","/");
    }

    public static String saveFile(BufferedImage bi, HttpServletRequest request) throws IOException {
        String saveFileName = UUID.randomUUID().toString() + ".png";
        File outputFile = new File(getFileSavePath("file"), saveFileName);
        ImageIO.write(bi, "png", outputFile);
        return outputFile.getAbsolutePath().replaceAll("\\\\","/");
    }

    public static String saveFile(MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String saveFileName = UUID.randomUUID().toString();
        if(!StringUtils.isEmpty(fileName)){
            saveFileName += fileName.substring(fileName.lastIndexOf("."));
        }
        File outputFile = new File(getFileSavePath("file"), saveFileName);
        try {
            file.transferTo(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath().replaceAll("\\\\","/");
    }

    private static File getFileSavePath(String type){
        String basePath = "C:/doc/" + type + "/";
        Date now = new Date();
        File dateFile = new File(basePath + CommonConst.SF.format(now));
        if(!dateFile.exists()){
            dateFile.mkdirs();
        }
        return dateFile;
    }
}
