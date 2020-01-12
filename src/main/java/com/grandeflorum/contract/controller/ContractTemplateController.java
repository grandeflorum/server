package com.grandeflorum.contract.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.domain.ContractEdit;
import com.grandeflorum.contract.domain.ContractTemplate;
import com.grandeflorum.contract.service.ContractTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("ContractTemplate")
public class ContractTemplateController {

    @Autowired
    private ContractTemplateService contractTemplateService;
    /**
     * 下载合同模板
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/downloadDocByEditor")
    public ResponseBo downloadDocByEditor(String id, String title , HttpServletResponse response) throws IOException {
        return contractTemplateService.downloadDocByEditor(id, title, response);
    }

    /**
     * 上传合同模板
     *
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadDoc")
    public ResponseBo uploadDoc(@RequestParam("files") MultipartFile files, HttpServletRequest request) {
        return contractTemplateService.uploadDoc(files, request);
    }

    /**
     * 保存合同模板
     *
     * @param contractTemplate
     * @return
     */
    @PostMapping("/SaveContractTemplate")
    public ResponseBo SaveContractTemplate(@RequestBody ContractTemplate contractTemplate) {
        return contractTemplateService.SaveContractTemplate(contractTemplate);
    }

    /**
     * 获取合同模板
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getContractTemplateById")
    public ResponseBo getContractTemplateById(String id) {
        return contractTemplateService.getContractTemplateById(id);
    }

    /**
     * 获取合同模板
     *
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/getContractTemplateByType")
    public ResponseBo getContractTemplateByType(Integer type) {
        return contractTemplateService.getContractTemplateByType(type);
    }

    /**
     * 获取合同模板修改历史
     *
     * @param page
     * @return
     */
    @PostMapping("/getContractTemplateHistoryList")
    public ResponseBo getContractTemplateHistoryList(@RequestBody Page page) {

        return contractTemplateService.getContractTemplateHistoryList(page);
    }

    /**
     * 获取合同模板历史
     *
     * @param id
     * @return
     */
    @GetMapping("/getContractTemplateHistoryById")
    public ResponseBo getContractTemplateHistoryById(String id) {

        return contractTemplateService.getContractTemplateHistoryById(id);
    }

    /**
     * 获取编辑的合同模板
     * @param tradeId
     * @return
     */
    @GetMapping("/getTradeEditByTradeId")
    public ResponseBo getTradeEditByTradeId(String tradeId,String type) {
        return contractTemplateService.getTradeEditByTradeId(tradeId,type);
    }

    @PostMapping("/saveTradeEdit")
    public ResponseBo saveTradeEdit(@RequestBody  ContractEdit contractEdit){
        return contractTemplateService.saveTradeEdit(contractEdit);
    }
}
