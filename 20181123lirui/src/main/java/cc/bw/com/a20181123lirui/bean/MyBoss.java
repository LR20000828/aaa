package cc.bw.com.a20181123lirui.bean;

import java.util.ArrayList;

public class MyBoss {
    private String msg;
    private String code;
    private ArrayList<MyData> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<MyData> getData() {
        return data;
    }

    public void setData(ArrayList<MyData> data) {
        this.data = data;
    }

    public MyBoss(String msg, String code, ArrayList<MyData> data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
