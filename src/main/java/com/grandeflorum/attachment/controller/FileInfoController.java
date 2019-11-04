package com.grandeflorum.attachment.controller;

import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.attachment.service.FileInfoService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 13260 on 2019/11/2.
 */
@RestController
@RequestMapping("FileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping(value = "/upload")
    public ResponseBo upload(@RequestParam("files") MultipartFile[] files,HttpServletRequest request){

        return fileInfoService.upload(files,request);
    }


    @GetMapping("/delete")
    public ResponseBo deleteFileById(String id){
        int num= fileInfoService.deleteByKey(id);
        return ResponseBo.ok();
    }

    @PostMapping("/deleteByIds")
    public ResponseBo deleteFileByIds(@RequestBody List<String> ids){
        int num= fileInfoService.batchDelete(ids,"id", FileInfo.class);
        return ResponseBo.ok();
    }

    @GetMapping("/getFileListById")
    public ResponseBo getFileList(String id){
        return fileInfoService.getFileList(id);
    }

    @GetMapping("/download")
    public void downloadAccessoryByid(String id,int type,HttpServletResponse response){
        fileInfoService.downloadAccessoryByid(id,type,response);
    }

    /**
     * 通过refid与类型查询附件列表（参数：refid，type）
     *
     * @param page
     * @return
     */
    @PostMapping("/getFileListByRefidAndType")
    public ResponseBo getFileListByRefidAndType(@RequestBody Page page) {

        return fileInfoService.getFileListByRefidAndType(page);
    }
}
