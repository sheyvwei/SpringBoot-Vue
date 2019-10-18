package cn.freemadao.enums;

/**
 * 枚举操作结果
 */
public enum ResultEnums {
    SUCCESS("操作成功"),
    ERROR("操作失败"),
    INNER_ERROR("系统异常"),
    PARAMETER_ERROR("入参失败"),
    LOGIN_ERROR("账号或密码错误"),
    LOGINFAIL_ERROR("登录已过期，请重新登录"),
    ACCESS_ERROR("权限不足");
    private String info;
    public String getInfo() {
        return  this.info;
    }
    ResultEnums(String info){
        this.info  =info;
    }
}
