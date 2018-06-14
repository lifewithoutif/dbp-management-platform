package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;

public class Ports implements Serializable {


    private int port;

    private String enabled;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Ports{" +
                "port=" + port +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
