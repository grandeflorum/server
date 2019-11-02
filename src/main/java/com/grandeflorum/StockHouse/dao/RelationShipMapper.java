package com.grandeflorum.StockHouse.dao;

import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.common.config.MyMapper;

import java.util.List;

public interface RelationShipMapper  extends MyMapper<RelationShip> {
    int deleteRelationShipByProjectId(String projectId);

    List<RelationShip>  getRelationShipByProjectId(String projectId);
}
