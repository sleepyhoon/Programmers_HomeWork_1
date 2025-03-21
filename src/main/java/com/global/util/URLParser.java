package com.global.util;

import java.util.HashMap;
import java.util.Map;

/*
    /controller/target -> none
    /controller/target?param1=value1 -> 단일 파라미터
    /controller/target?param1=value1&param2=value2 -> 복합 파라미터는 맨 끝만 선택
    여기서 유효성 검사?
 */
public class URLParser {
    private String URL;
    private String controllerCode;
    private String target;

    private final Map<String, Object> parameters = new HashMap<>();
    private boolean isValidURL = true;

    public URLParser(String url) {
        this.URL = parsing(url);
    }

    protected String parsing(String url) {
        if (!url.startsWith("/")) {
            isValidURL = false;
            return url;
        }

        String[] splitURL = url.split("\\?", 2);

        if (splitURL.length == 2) {
            setParameters(splitURL[1]);
        }

        String[] urlFront = splitURL[0].split("/");

        if (urlFront.length != 3) {
            isValidURL = false;
            return url;
        }

        // /controller/target => ["", "controller", "target"]
        controllerCode = urlFront[1];
        target = urlFront[2];
        return url;
    }

    protected void setParameters(String urlPart) {
        try {
            if (urlPart.contains("&")) {
                String[] split = urlPart.split("&");
                for (String s : split) {
                    String[] parameterData = s.split("=", 2);

                    if (parameterData[1].isEmpty()) {
                        throw new IllegalArgumentException("잘못된 파라미터 값입니다. URL을 확인해주세요");
                    }
                    parameters.put(parameterData[0], parameterData[1]);
                }
            } else {
                // param1=valu==========e1
                // param1=
                String[] split = urlPart.split("=", 2);
                if (split[1].isEmpty()) {
                    throw new IllegalArgumentException("잘못된 파라미터 값입니다. URL을 확인해주세요");
                }
                parameters.put(split[0], split[1]);
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            isValidURL = false;
        }
    }

    protected Map<String, Object> getParameters() {
        return parameters;
    }

    protected String getURL() {
        return URL;
    }

    protected String getControllerCode() {
        return controllerCode;
    }

    protected String getTarget() {
        return target;
    }

    protected boolean isValidURL() {
        return isValidURL;
    }
}
