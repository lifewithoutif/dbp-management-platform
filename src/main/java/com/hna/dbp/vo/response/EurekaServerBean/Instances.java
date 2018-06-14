package com.hna.dbp.vo.response.EurekaServerBean;

import com.netflix.appinfo.InstanceInfo;

public class Instances {

    private InstanceInfo instance;

    public InstanceInfo getInstance() {
        return instance;
    }

    public void setInstance(InstanceInfo instance) {
        this.instance = instance;
    }
}
