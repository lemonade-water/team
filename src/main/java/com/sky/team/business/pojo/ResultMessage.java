package com.sky.team.business.pojo;

import java.io.Serializable;

public class ResultMessage implements Serializable {


    private String code;
    private String msg;

    public ResultMessage() {
    }

    public ResultMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResultMessage setResultMessage(String code,String msg){
        return new ResultMessage(code,msg);
    }
}
