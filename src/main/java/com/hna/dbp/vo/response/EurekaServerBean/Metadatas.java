package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;

public class Metadatas implements Serializable {



    private String aclass;

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    @Override
    public String toString() {
        return "Metadatas{" +
                "aclass='" + aclass + '\'' +
                '}';
    }
}
