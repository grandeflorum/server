package com.grandeflorum.buildingTable.service;

import com.grandeflorum.buildingTable.domain.LJZ;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;

import javax.servlet.http.HttpServletResponse;

public interface BuildingTableService {

    ResponseBo getBuildingTableList(Page page);

    ResponseBo getLjz(String id);

    ResponseBo getZrz(String id);

    ResponseBo getInfoByZh(String ZH,String Type);

    void printHt(String id,int type,HttpServletResponse response);

}
