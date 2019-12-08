package com.grandeflorum.contract.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.contract.dao.ContractTemplateHistoryMapper;
import com.grandeflorum.contract.dao.ContractTemplateMapper;
import com.grandeflorum.attachment.dao.FileInfoMapper;
import com.grandeflorum.contract.domain.ContractTemplate;
import com.grandeflorum.contract.domain.ContractTemplateHistory;
import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.attachment.service.FileInfoService;
import com.grandeflorum.common.config.GrandeflorumProperties;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.common.util.WordHelper;
import com.grandeflorum.contract.service.ContractTemplateService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 13260 on 2019/11/2.
 */
@Service("ContractTemplateService")
public class ContractTemplateServiceImpl extends BaseService<ContractTemplate> implements ContractTemplateService {

    @Autowired
    private GrandeflorumProperties grandeflorumProperties;

    @Autowired
    private ContractTemplateMapper contractTemplateMapper;

    @Autowired
    private ContractTemplateHistoryMapper contractTemplateHistoryMapper;

    @Override
    public String GetFileStorageFolder(String actualFile) {
//        RainbowProperties rp = new RainbowProperties();

        String webInfPath = grandeflorumProperties.getUploadFolder();

        String path = String.format("%s%s/%s/", webInfPath, actualFile.charAt(0), actualFile.charAt(1));
        return path;
    }

    @Override
    public ResponseBo uploadDoc(MultipartFile multifile, HttpServletRequest request) {
        List<String> results = new ArrayList<>();
        if (null == multifile) {
            return ResponseBo.error();
        }
        String guid = UUID.randomUUID().toString();
        String ext = null;
        String storeFile = null;
        String fileName = null;
        String fileSavePath = "";
        String storageFolder = "";
        ContractTemplate contractTemplate=null;
        try {
            fileName = StrUtil.isNullOrEmpty(fileName) ? multifile.getOriginalFilename() : fileName;
            fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
            ext = FilenameUtils.getExtension(fileName);
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
            String content = WordHelper.wordToHtml(fileSavePath);
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            if (id == null || id.equals("")) {
                 contractTemplate = new ContractTemplate();
                contractTemplate.setId(GuidHelper.getGuid());
                contractTemplate.setContent(content);
                contractTemplate.setUploadDate(new Date());
                if (type.equals("1")) {
                    contractTemplate.setType(1);
                    contractTemplate.setName("商品房买卖合同（现售）模板");
                } else if (type.equals("2")) {
                    contractTemplate.setType(2);
                    contractTemplate.setName("商品房买卖合同（预售）模板");
                }
                else if (type.equals("3")) {
                    contractTemplate.setType(3);
                    contractTemplate.setName("存量房合同模板");
                }
                contractTemplateMapper.insert(contractTemplate);
            } else {
                 contractTemplate = contractTemplateMapper.selectByPrimaryKey(id);
                contractTemplate.setContent(content);
                contractTemplateMapper.updateByPrimaryKey(contractTemplate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error();
        }
        return ResponseBo.ok(contractTemplate);
    }


    @Override
    public ResponseBo downloadDocByEditor(String id, String title, HttpServletResponse response) throws IOException {
        ContractTemplate contractTemplate = contractTemplateMapper.selectByPrimaryKey(id);
        String content = contractTemplate.getContent();
        String str = " <!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val='Cambria Math'/><m:brkBin m:val='before'/><m:brkBinSub m:val='--'/><m:smallFrac m:val='off'/><m:dispDef/><m:lMargin m:val='0'/> <m:rMargin m:val='0'/><m:defJc m:val='centerGroup'/><m:wrapIndent m:val='1440'/><m:intLim m:val='subSup'/><m:naryLim m:val='undOvr'/></m:mathPr></w:WordDocument></xml><![endif]-->";
        //其中content为ueditor生成的内容
        String h = " <html xmlns:v='urn:schemas-microsoft-com:vml'xmlns:o='urn:schemas-microsoft-com:office:office'xmlns:w='urn:schemas-microsoft-com:office:word'xmlns:m='http://schemas.microsoft.com/office/2004/12/omml'xmlns='http://www.w3.org/TR/REC-html40'  ";
        content = h + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" + str + "</head><body>" + content + "</body> </html>";
        byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
        ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
        //输出文件
        response.setContentType("application/msword");//导出word格式
        response.addHeader("Content-Disposition", "attachment;filename=" +
                new String(title.getBytes("GB2312"), "iso8859-1") + ".doc");
        ServletOutputStream ostream = response.getOutputStream();
        poifs.writeFilesystem(ostream);
        return ResponseBo.ok();
    }


    @Override
    public ResponseBo SaveContractTemplate(ContractTemplate contractTemplate) {
        if (contractTemplate.getId() == null || contractTemplate.getId().equals("")) {
            contractTemplate.setId(GuidHelper.getGuid());
            contractTemplate.setUploadDate(new Date());
            contractTemplateMapper.insert(contractTemplate);
        } else {
            contractTemplateMapper.updateByPrimaryKey(contractTemplate);
            if (contractTemplate.getContractTemplateHistory() != null) {
                //修改合同模板时要添加修改记录
                contractTemplate.getContractTemplateHistory().setId(GuidHelper.getGuid());
                contractTemplate.getContractTemplateHistory().setContractTemplateId(contractTemplate.getId());
                contractTemplate.getContractTemplateHistory().setUploadDate(new Date());
                contractTemplate.getContractTemplateHistory().setContent(contractTemplate.getContent());
                contractTemplateHistoryMapper.insert(contractTemplate.getContractTemplateHistory());
            }

        }
        return ResponseBo.ok(contractTemplate.getId());
    }

    @Override
    public ResponseBo getContractTemplateById(String id) {

        ContractTemplate contractTemplate = contractTemplateMapper.selectByPrimaryKey(id);
        return ResponseBo.ok(contractTemplate);
    }

    @Override
    public ResponseBo getContractTemplateByType(Integer type) {
        ContractTemplate contractTemplate = contractTemplateMapper.getContractTemplateByType(type);
        return ResponseBo.ok(contractTemplate);
    }


    @Override
    public ResponseBo getContractTemplateHistoryList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ContractTemplateHistory> list = contractTemplateHistoryMapper.getContractTemplateHistoryList(map);

        PageInfo<ContractTemplateHistory> pageInfo = new PageInfo<ContractTemplateHistory>(list);

        PagingEntity<ContractTemplateHistory> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getContractTemplateHistoryById(String id) {
        ContractTemplateHistory contractTemplateHistory = contractTemplateHistoryMapper.selectByPrimaryKey(id);
        if (contractTemplateHistory != null) {
            return ResponseBo.ok(contractTemplateHistory);
        } else {
            return ResponseBo.error();
        }
    }


}
