package utils;

import com.google.gson.Gson;

public class GsonTools {
	 /*
	  　　     * Function　:   生成JSON字符串
	  　　     * Param 　　:　  value     想要转换成JSON字符串的Object对象
	 　　     * Retuen　　:   JSON字符串
	 　　     * Author　　:   博客园-依旧淡然
	 　　     */
	    public static String createJsonString(Object value) {
	       Gson gson = new Gson();
	        String string = gson.toJson(value);
	       return string;
	   }
}
