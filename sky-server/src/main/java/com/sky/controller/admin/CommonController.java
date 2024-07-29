package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sky.utils.AliOssUtil;

import java.io.IOException;
import java.util.UUID;

/**
 *  通用接口
 */

@RestController /*Rest控制器 注解的类中的每个方法都会自动将返回值写入 HTTP 响应体*/
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
/*CommonController是一个使用AliOssUtil进行文件上传的控制器类。*/
public class CommonController {
    //泛型 文件上传路径 file与前端保持一致
    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);
        try{
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名
            /*使用UUID.randomUUID().toString()生成一个随机的文件名，并将后缀拼接在文件名后面，构造出新的文件名。*/
            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        }catch (IOException e){
                log.error("文件上传失败：{}",e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
    //在数据库里面存了文件的地址 可以通过URL访问

}
