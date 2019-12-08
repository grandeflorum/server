package com.grandeflorum.buildingTable.dao;

import com.grandeflorum.buildingTable.domain.*;

import java.util.List;
import java.util.Map;

public interface BuildingTableMapper {

    List<ResultList> getBuildingTableList(Map<String,Object> map);

    List<C> getCList(Map<String,Object> map);

    List<H> getHList(Map<String,Object> map);

    LJZ getLjz(String id);

    List<LJZ> getLjzList(String zrzh);

    ZRZ getZrz(String id);

    C getC(String id);

    H getH(String id);

    List<String> getZrzId(String zh);

    List<String> getLjzId(String zh);

    String getTradeIdByHouseId(Map<String,Object> map);

    List<H> getChildHList (Map<String,Object> map);

    void auditZRZById(Map<String,Object> map);

}
