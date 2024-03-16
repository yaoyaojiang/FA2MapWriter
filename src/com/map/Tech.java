package com.map;

public class Tech {
    String code;
    int techlevel;
    int belong;//1盟，2苏，3尤，4平

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTechlevel() {
        return techlevel;
    }

    public void setTechlevel(int techlevel) {
        this.techlevel = techlevel;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    @Override
    public String toString() {
        return "Tech{" +
                "code='" + code + '\'' +
                ", techlevel=" + techlevel +
                ", belong=" + belong +
                '}';
    }
}
