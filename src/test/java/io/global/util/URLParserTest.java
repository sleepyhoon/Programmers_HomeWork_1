package io.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class URLParserTest {
    @Test
    @DisplayName("요청 테스트")
    void test1() throws Exception {
        String command = "/controller/target?id=1";
        UserRequest request = new UserRequest(command);
        boolean valid = request.isValid();
        System.out.println("valid = " + (valid == true));

        Integer value = request.getValue("id", Integer.class);
        System.out.println("value = " + (value == 1));
    }

    @Test
    @DisplayName("board test")
    void test2() throws Exception {
        String command = "/boards/add";
        UserRequest request = new UserRequest(command);
        System.out.println(request.getControllerCode());
        System.out.println(request.getTarget());
    }
}