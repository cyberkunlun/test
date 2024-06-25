package com.esct.im_platform.test;


import java.util.HashMap;
import java.util.Map;

public class testhandler {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custom_key", "custom_value");
        IOSPush tool = new IOSPush();
        tool.push("9e9230c12238df839e3c4cd2a0261742c514f3ea1f4bcc57e8fda71f8fb4a044", "alertTitle", "alertBody", true, map, 1);
    }
}
