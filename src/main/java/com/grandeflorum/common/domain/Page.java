package com.grandeflorum.common.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/6/30.
 */
public class Page {

    public int pageNo;

    public int pageSize;

    public List<Condition> conditions;

    public int getStartNum() {
        return this.pageSize * (this.pageNo - 1) + 1;
    }

    public int getEndNum() {
        return this.pageSize * this.pageNo;
    }

    public Map<String, Object> getQueryParameter() {
        Map<String, Object> map = new HashMap();
        map.put("startNum", getStartNum());
        map.put("endNum", getEndNum());
        if (getConditions() != null) {
            for (final Condition cond : getConditions()) {
                Object obj = cond.getValue();
                if(obj instanceof String){
                    obj = ((String) obj).trim();
                }
                map.put(cond.getKey(), obj);
            }
        }
        return map;
    }

    public Map<String, Object> getQueryCondition() {
        return getQueryParameter();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
