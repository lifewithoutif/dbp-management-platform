package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;

//数据中心表
public class DataCenterInfo implements Serializable {

    private String name;
    private String aclass;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }
}
