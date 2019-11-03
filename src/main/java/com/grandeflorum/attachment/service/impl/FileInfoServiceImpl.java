package com.grandeflorum.attachment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.attachment.dao.FileInfoMapper;
import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.attachment.service.FileInfoService;
import com.grandeflorum.common.config.GrandeflorumProperties;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.StrUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 13260 on 2019/11/2.
 */
@Service("FileInfoService")
public class FileInfoServiceImpl extends BaseService<FileInfo> implements FileInfoService{

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private GrandeflorumProperties grandeflorumProperties;

    @Override
    public ResponseBo upload(MultipartFile[] multifiles, HttpServletRequest request) {

        String fromID = request.getParameter("refid");
        String fileType = request.getParameter("type");

        List<String> results = new ArrayList<>();
        if (null == multifiles||multifiles.length==0) {
            return ResponseBo.error();
        }

        for (MultipartFile multi:multifiles
             ) {
            MultipartFile multifile = multi;
            String guid = UUID.randomUUID().toString();
            results.add(guid);

            String ext = null;
            String storeFile = null;
            String fileName = null;
            String fileSavePath = "";
            String storageFolder="";

            try {
                fileName = StrUtil.isNullOrEmpty(fileName) ? multifile.getOriginalFilename() : fileName;
                fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
                ext = FilenameUtils.getExtension(fileName); //fileName.split("\\.")[1];
                storeFile = guid + "." + ext;

                // 保存文件
                storageFolder = GetFileStorageFolder(guid);
                fileSavePath = storageFolder + storeFile;
                File file = new File(storageFolder);

                if (!file.exists()) {
                    file.mkdirs();
                }

                File file1 = new File(fileSavePath);
                multifile.transferTo(file1);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileInfo fileInfo = new FileInfo();
            fileInfo.setRefId(fromID);
            fileInfo.setId(guid);
            fileInfo.setFileSuffix(ext);
            fileInfo.setServerFileName(storeFile);
            fileInfo.setClientFileName(fileName);
            fileInfo.setServerPath(fileSavePath);
            fileInfo.setUploadDate(new Date());
            fileInfo.setFileType(Short.parseShort(fileType));
            fileInfoMapper.insert(fileInfo);
        }



        return ResponseBo.ok(results);
    }


    @Override
    public String GetFileStorageFolder(String actualFile) {
//        RainbowProperties rp = new RainbowProperties();

        String webInfPath = grandeflorumProperties.getUploadFolder();

        String path = String.format("%s%s/%s/", webInfPath, actualFile.charAt(0), actualFile.charAt(1));
        return path;
    }

    @Override
    public ResponseBo getFileListByRefidAndType(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FileInfo> list = fileInfoMapper.getFileListByRefidAndType(map);

        PageInfo<FileInfo> pageInfo = new PageInfo<FileInfo>(list);

        PagingEntity<FileInfo> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFileList(String id) {

        Example example = new Example(FileInfo.class);
        example.createCriteria().andEqualTo("refId", id);
        List<FileInfo> list = fileInfoMapper.selectByExample(example);
        return ResponseBo.ok(list);

    }

    @Override
    public void downloadAccessoryByid(String id, int type,HttpServletResponse response) {

        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);

        if (fileInfo != null) {
            String path = fileInfo.getServerPath();

            //pdf
            if(type==2){
                path = path.substring(0,path.lastIndexOf("."))+".pdf";
            }

            File file = new File(path);

            if (!file.exists()) {
                return;
            }

            try {
                response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(fileInfo.getClientFileName(), "utf-8"));
                OutputStream out = response.getOutputStream();
                out.write(FileUtils.readFileToByteArray(file));
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void updateFileInfoByIds(List<FileInfo> list, String id) {
        if (list != null && list.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("fileIds", list);
            fileInfoMapper.updateFileInfoByIds(map);
        }
    }
}
