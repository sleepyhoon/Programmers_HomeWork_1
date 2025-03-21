package com.global.util;

import java.util.Map;

public class UserRequest {
    private URLParser parser;
    private Map<String,Object> parameters;

    public UserRequest(String url) {
        this.parser = new URLParser(url);
        this.parameters = this.parser.getParameters();
    }

    public boolean isValid() {
        return this.parser.isValidURL();
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String key, Class<T> cls) {
        // Object -> 뭐든지 바꿀 수 있음.
        Object value = parameters.get(key);
        if(cls == Integer.class) {
            return cls.cast(Integer.parseInt(value.toString()));
        } else if (cls == Long.class) {
            return cls.cast(Long.parseLong(value.toString()));
        } else if (cls == Boolean.class) {
            return cls.cast(Boolean.parseBoolean(value.toString()));
        }
        return cls.cast(value.toString());
    }

    public String getTarget() {
        return this.parser.getTarget();
    }

    public String getControllerCode() {
        return this.parser.getControllerCode();
    }
}
