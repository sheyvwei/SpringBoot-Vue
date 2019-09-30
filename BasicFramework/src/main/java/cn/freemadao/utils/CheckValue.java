package cn.freemadao.utils;


/**
 * 用来检查一些常用的数据 id...
 */

public class CheckValue {
    public static boolean checkId(Integer id) {
        if (id != null || id != 0) {
            return true;
        }
        return false;
    }
    public static boolean checkIds(Integer... ids){
        if(ids != null && ids.length > 0) {
            return  true;
        }
        return false;
    }
    public static boolean checkString(String str){
        if(str != null && str.length() > 0) {
            return true;
        }
        return false;
    }
    public static boolean checkObj(Object obj) {
        if(obj != null){
            return  true;
        }
        return false;
    }

}
