package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;


//请求数据表
public class Datas implements Serializable {

    private Applications applications;

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Datas{" +
                "applications='" + applications + '\'' +
                '}';
    }
}
