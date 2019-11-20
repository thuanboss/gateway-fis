package com.ftu.gateway.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationAndAuthorizationFilterTest {

    @Test
    public void test(){
        UriTemplate template1 = new UriTemplate("/identity-service/api/a" + "/{var}");
        String url = "/identity-service/api/a/1234/api2/1234";
        System.out.println(template1.matches(url));
    }
}
