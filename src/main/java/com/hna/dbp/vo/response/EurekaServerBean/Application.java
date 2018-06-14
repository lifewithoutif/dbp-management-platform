package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;
import java.util.List;

//应用表
public class Application implements Serializable {


    private String name;

    private List<Instance> instance;

    private int num;

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", instance=" + instance +
                ", num=" + num +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instance> getInstance() {
        return instance;
    }

    public void setInstance(List<Instance> instance) {
        this.instance = instance;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
