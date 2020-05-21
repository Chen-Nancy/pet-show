package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.constants.FileConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.util.ParameterUtil;
import com.nancy.petshow.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author chen
 * @date 2020/5/15 15:28
 */
@Controller
@Validated
@RequestMapping("file")
@Api(tags = "文件操作接口")
public class FileController {
    private static Logger log = LoggerFactory.getLogger(FileController.class);

    /**
     * 图片展示
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @ApiIgnore
    @RequestMapping("show/*/*")
    @ResponseBody
    public void show(HttpServletResponse response, HttpServletRequest request) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String uri = request.getRequestURI();
            String imgName = uri.substring(FileConstants.FILE_SHOW_PRE.length());
            String fileAddress = FileConstants.FILE_SAVE_ADDRESS + imgName;
            File file = new File(fileAddress);
            if (!file.exists()) {
                return;
            }
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            int read = -1;
            byte[] bytes = new byte[1024];
            while ((read = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (bos != null) {
                bos.flush();
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }

    /**
     * 文件下载
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @ApiIgnore
    @RequestMapping("download/*/*")
    @ResponseBody
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String uri = URLDecoder.decode(request.getRequestURI(), "utf-8");
            String fileName = uri.substring(FileConstants.FILE_DOWNLOAD_PRE.length());
            //设置文件下载响应头
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            //开始读取文件
            String fileAddress = FileConstants.FILE_SAVE_ADDRESS + fileName;
            File file = new File(fileAddress);
            if (!file.exists()) {
                return;
            }
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            int read = -1;
            byte[] bytes = new byte[1024];
            while ((read = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (bos != null) {
                bos.flush();
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }

    /**
     * 文件上传
     *
     * @param type        文件类型：0展示、1下载
     * @param addressType 文件分类：0用户、1帖子
     * @param file        文件
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "文件上传", notes = "/file/upload", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorize", value = "token", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型：0展示文件、1可下载文件", required = true, paramType = "query", dataType = "byte"),
            @ApiImplicitParam(name = "addressType", value = "分类：0用户图片、1帖子图片", required = true, paramType = "query", dataType = "byte"),
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "query")
    })
    @PostMapping("upload")
    @ResponseBody
    public Result upload(@NotNull Byte type, @NotNull Byte addressType, @NotNull MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return new Result(CodeConstants.ERROR_CODE, "请上传文件");
        }
        String address = null;
        switch (addressType) {
            case 0:
                address = FileConstants.USER_FILE_ADDRESS;
                break;
            case 1:
                address = FileConstants.TOPIC_FILE_ADDRESS;
                break;
            default:
                return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
        }
        String fileName = file.getOriginalFilename();
        if (ParameterUtil.stringIsEmpty(fileName)) {
            return new Result(CodeConstants.ERROR_CODE, "参数错误");
        }
        fileName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUIDUtil.getUUIDByNum(12) + System.currentTimeMillis() + fileName;
        File f = new File(FileConstants.FILE_SAVE_ADDRESS + address + fileName);
        file.transferTo(f);
        String fileUri = null;
        switch (type) {
            case 0:
                fileUri = FileConstants.FILE_SHOW_PRE + address + fileName;
                log.info("文件上传成功：" + fileUri);
                return new Result(CodeConstants.SUCCESS_CODE, "success", fileUri);
            case 1:
                fileUri = FileConstants.FILE_DOWNLOAD_PRE + address + fileName;
                log.info("文件上传成功：" + fileUri);
                return new Result(CodeConstants.SUCCESS_CODE, "success", fileUri);
            default:
                return new Result(CodeConstants.ERROR_CODE, "参数错误");
        }
    }
}
