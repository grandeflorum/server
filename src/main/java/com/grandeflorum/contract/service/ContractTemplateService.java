package com.grandeflorum.contract.service;

import com.grandeflorum.contract.domain.ContractEdit;
import com.grandeflorum.contract.domain.ContractTemplate;
import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ContractTemplateService extends IService<ContractTemplate>{

    String GetFileStorageFolder(String id);

    ResponseBo  downloadDocByEditor(String id, String title ,HttpServletResponse response) throws IOException;

    ResponseBo SaveContractTemplate(ContractTemplate contractTemplate);

    ResponseBo getContractTemplateById(String id) ;

    ResponseBo getContractTemplateByType(Integer type);

    ResponseBo getContractTemplateHistoryList( Page page);

    ResponseBo  uploadDoc(MultipartFile file, HttpServletRequest request);

    ResponseBo getContractTemplateHistoryById(String id);

    ResponseBo getTradeEditByTradeId(String tradeId,String type);

    ResponseBo saveTradeEdit(ContractEdit contractEdit);
}


