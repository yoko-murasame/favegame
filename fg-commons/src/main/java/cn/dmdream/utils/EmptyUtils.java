package cn.dmdream.utils;

import java.util.Collection;
import java.util.Map;

public class EmptyUtils {

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {

        try {
            //普通对象
            if (object == null) {
                return true;
            }

            //字符串
            if (object instanceof String){
                if (object.toString().trim().equals("") || object == null) {
                    return true;
                }
            }

            //集合
            if (object instanceof Collection || object instanceof Map) {

                if (object instanceof Map) {
                    return ((Map) object).isEmpty();
                }

                if (((Collection) object).size() == 0){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
