package com.grandeflorum.attachment.service;

import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 13260 on 2019/11/2.
 */
public interface FileInfoService extends IService<FileInfo>{

    ResponseBo upload(MultipartFile[] file, HttpServletRequest request);

    ResponseBo getFileList(String id);

    void downloadAccessoryByid(String id,int type,HttpServletResponse response);

    void updateFileInfoByIds(List<FileInfo> list, String id);

    String GetFileStorageFolder(String id);

    ResponseBo getFileListByRefidAndType(Page page);

    ResponseBo getAttachDicCount(String id,String type);
}


