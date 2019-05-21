package com.food.bean;

public class path {
    private int sapid;
    private String path;
    private String name;
    private String time;
   public path(){}
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private String type;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSapid(int sapid) {
        this.sapid = sapid;
    }

    public int getSapid() {
        return sapid;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
