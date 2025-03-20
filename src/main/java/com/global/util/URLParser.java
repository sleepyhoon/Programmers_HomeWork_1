package com.global.util;

public class URLParser {
    public static String[] parsing(String url) {
        return url.split("[/?]");
    }
}
