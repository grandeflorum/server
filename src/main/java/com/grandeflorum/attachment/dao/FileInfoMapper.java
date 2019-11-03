package com.grandeflorum.attachment.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.attachment.domain.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileInfoMapper extends MyMapper<FileInfo> {

    int updateFileInfoByIds(Map<String,Object> map);

    List<FileInfo> getFileListByRefidAndType(Map<String, Object> map);
}