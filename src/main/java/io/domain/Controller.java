package io.domain;

import io.global.util.UserRequest;

public interface Controller {
    void requestHandle(UserRequest request);
}
