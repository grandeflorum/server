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

}
