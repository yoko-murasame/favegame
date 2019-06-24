package cn.dmdream.utils;

/**
 * 随机生成校验码
 */
public class RandomCode {

    public static int genCode(){
        int code = (int)((Math.random()*9+1)*100000);
        return code;
    }

    public static void main(String[] args) {
        System.out.println(RandomCode.genCode());
    }
}
