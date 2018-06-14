package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;

public class SecurePort implements Serializable {

    private int port;

    private String enabled;

    @Override
    public String toString() {
        return "SecurePort{" +
                "port=" + port +
                ", enabled='" + enabled + '\'' +
                '}';
    }

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
}
