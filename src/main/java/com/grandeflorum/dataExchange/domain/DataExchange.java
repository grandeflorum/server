package com.grandeflorum.dataExchange.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/28.
 */
public class DataExchange {


    private boolean Flag;


    private List<Map<String,Object>> Data;


    private String Message;

    @JsonProperty("Flag")
    public boolean isFlag() {
        return Flag;
    }

    public void setFlag(boolean flag) {
        Flag = flag;
    }

    @JsonProperty("Data")
    public List<Map<String, Object>> getData() {
        return Data;
    }

    public void setData(List<Map<String, Object>> data) {
        Data = data;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
