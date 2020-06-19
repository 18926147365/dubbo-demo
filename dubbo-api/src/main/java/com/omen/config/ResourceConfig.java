package com.omen.config;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/19 17:07
 */
public class ResourceConfig {

    private static Map<String,String> cacheMap=new HashMap<>();

    public static String get(String name,String key){
        String _val=cacheMap.get(key);
        if(StringUtils.isBlank(_val)){
            synchronized (key.intern()){
                System.out.println("du");
                ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault());
                return rb.getString(key);
            }
        }
        return _val;
    }
    public static final String getVersion(String key){
        return  get("server-version",key+".version");
    }

    public static void main(String[] args) {
        String string=getVersion("PayService");
        System.out.println(string);
    }
}
