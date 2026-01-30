package com.pm.authservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "jwt.secret=c2hvb3RtYWpvcnB1dHN1bWNvbGxlZ2VsYXJnZXJub3RoaW5nZW5naW5lZXJiZWFudmU="
})
class AuthServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
