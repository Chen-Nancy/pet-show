package com.nancy.petshow.util;

import com.nancy.petshow.constants.FileConstants;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author chen
 * @date 2020/5/11 13:59
 */
public class FileUtil {
    /**
     * 文件保存
     *
     * @param file        文件
     * @param fileAddress 文件保存地址
     * @param dataAddress 数据库保存地址
     * @return
     * @throws IOException
     */
    public static String fileSave(MultipartFile file, String fileAddress, String dataAddress) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        if (ParameterUtil.stringIsEmpty(fileName)) {
            return null;
        }
        fileName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUIDUtil.getUUIDByNum(12) + System.currentTimeMillis() + fileName;
        File f = new File(FileConstants.FILE_SAVE_ADDRESS + fileAddress + fileName);
        file.transferTo(f);
        return dataAddress + fileAddress + fileName;
    }
}
