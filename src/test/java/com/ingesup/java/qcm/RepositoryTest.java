package com.ingesup.java.qcm;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lopes_f on 2/22/2015.
 * <florian.lopes@outlook.com>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RunWith (SpringJUnit4ClassRunner.class)
@ActiveProfiles (value = "test")
@SpringApplicationConfiguration (classes = JavaQcmApplication.class)
@Sql(scripts = "resources/sql/test-data.sql")
public @interface RepositoryTest {
}
