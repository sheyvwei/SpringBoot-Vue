package cn.freemadao.enums;

/**
 * 结果状态码
 */

public class StatusCode {

    public static final int SUCCESS = 20000; //成功
    public static final int ERROR = 20001; //失败
    public static final int LOGINE_RROR = 20002; //用户名或密码错误
    public static final int ACCESS_ERROR = 20003; //权限不足
    public static final int REPEE_RROR = 20004; //重复操作
    public static final int PARAMETER_ERROR = 20005; //入参错误
    public static final int LOGINFAIL_ERROR = 20006; //登录失效,
}
