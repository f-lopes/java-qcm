package com.ingesup.java.qcm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JavaQcmApplication.class)
@WebAppConfiguration
public class JavaQcmApplicationTests {

	@Test
	public void contextLoads() {
	}

}
