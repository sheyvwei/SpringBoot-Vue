package cn.freemadao.dto;


import cn.freemadao.enums.ResultEnums;

/**
 * 对结果数据进行封装给前端
 */
public class Result {
    private Integer code;   //状态码
    private Object data;    //结果
    public Result(){

    }
    public Result(Integer code, Object data){
        this.code = code;
        this.data = data;
    }
//    public Result(Integer code, ResultEnums resultEnums){
//        this.code = code;
//        this.data = resultEnums.getInfo();
//    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
