package com.grandeflorum.buildingTable.service;

import com.grandeflorum.buildingTable.domain.LJZ;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;

public interface BuildingTableService {

    ResponseBo getBuildingTableList(Page page);

    ResponseBo getLjz(String id);

    ResponseBo getZrz(String id);

}
