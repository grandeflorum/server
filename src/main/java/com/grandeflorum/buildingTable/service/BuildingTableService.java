package com.grandeflorum.buildingTable.service;

import com.grandeflorum.buildingTable.domain.C;
import com.grandeflorum.buildingTable.domain.H;
import com.grandeflorum.buildingTable.domain.LJZ;
import com.grandeflorum.buildingTable.domain.ZRZ;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BuildingTableService {

    ResponseBo getBuildingTableList(Page page);

    ResponseBo getLjz(String id);

    ResponseBo getZrz(String id);

    ResponseBo getInfoByZh(String ZH,String Type);

    void printHt(String id,int type,HttpServletResponse response);

    ResponseBo saveOrUpdateZRZ(ZRZ zrz);

    ResponseBo getZRZById(String id);

    ResponseBo saveOrUpdateLJZ(LJZ ljz);

    ResponseBo getLJZById(String id);

    ResponseBo saveOrUpdateC(C c);

    ResponseBo getCById(String id);

    ResponseBo deleteC(String id);

    ResponseBo saveOrUpdateH(H h);

    ResponseBo getHById(String id);

    ResponseBo deleteH(String id);

    ResponseBo getChildHList(Page page);


}
