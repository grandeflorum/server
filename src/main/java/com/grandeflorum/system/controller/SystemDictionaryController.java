package com.grandeflorum.system.controller;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.system.domain.DataDictionaryItem;
import com.grandeflorum.system.service.SystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("SystemDictionary")
public class SystemDictionaryController {
    @Autowired
    private SystemDictionaryService systemDictionaryService;

    /**
     * 获取全部菜单信息
     *
     * @param
     * @return
     */
    @PostMapping("/getAllDictionary")
    public ResponseBo getAllDictionary() {

        Map<String, List<DataDictionaryItem>> result = systemDictionaryService.getAllDictionary();
        return ResponseBo.ok(result);
    }
}
