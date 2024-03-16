package com.map;

public class SW {
    int code;
    String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SW{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
