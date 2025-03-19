package com.global.util;

public class URLParser {
    private static final String BOARD_URL_START = "https://";

    public static String[] parsing(String url) {
        url = url.substring(8);
        return url.split("[/?]");
    }
}
