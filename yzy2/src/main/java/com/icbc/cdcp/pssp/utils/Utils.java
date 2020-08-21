package com.icbc.cdcp.pssp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Utils {
    private static final HashMap<String, Object> cache = new HashMap<>();

    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str);
    }

    public static Object getCache(String key) {
        return cache.get(key);
    }

    public static void setCache(String key, Object value) {
        cache.put(key, value);
    }

    public static void removeCache(String key) {
        cache.remove(key);
    }
}
