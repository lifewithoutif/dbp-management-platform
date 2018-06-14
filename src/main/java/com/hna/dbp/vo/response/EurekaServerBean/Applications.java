package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;
import java.util.List;

public class Applications implements Serializable {

    private String versions__delta;

    private String apps__hashcode;

    private List<Application> application;

    public String getVersions__delta() {
        return versions__delta;
    }

    public void setVersions__delta(String versions__delta) {
        this.versions__delta = versions__delta;
    }

    public String getApps__hashcode() {
        return apps__hashcode;
    }

    public void setApps__hashcode(String apps__hashcode) {
        this.apps__hashcode = apps__hashcode;
    }

    public List<Application> getApplication() {
        return application;
    }

    public void setApplication(List<Application> application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Applications{" +
                "versions__delta='" + versions__delta + '\'' +
                ", apps__hashcode='" + apps__hashcode + '\'' +
                ", application=" + application +
                '}';
    }
}
