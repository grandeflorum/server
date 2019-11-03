package com.grandeflorum.project.dao;

import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface ProjectManualMapper extends MyMapper<FileInfo> {
    List<FileInfo> getProjectManualList(Map<String, Object> map);
}
