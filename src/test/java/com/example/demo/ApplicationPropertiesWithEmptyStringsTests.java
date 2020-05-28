package com.example.demo;

import com.example.demo.config.ApplicationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * application-test.yaml
 *
 * application:
 *   a: "some string"
 *   b:
 *     - 1
 *     - 2
 *     - 3
 *   c:
 *     - "one"
 *     - "two"
 *     - "three"
 *   m:
 *     - one: 1
 *     - two: 2
 *
 * We are trying to override
 * - string property with empty string "",
 * - list properties with empty string "",
 * - map properties with empty string ""
 *
 * SPRING_APPLICATION_JSON = {
 *     "application": {
 *         "a": "",
 *         "b": "",
 *         "c": "",
 *         "m": ""
 *     }
 * }
 *
 *
 * Expecting                            Actual
 *  a == null                           a == ""
 *  b == null | empty array list        b == []
 *  c == null | empty array list        c == []
 *  m == null | empty map               m == {}
 *
 */
@ActiveProfiles("test")
@TestPropertySource(properties = {"SPRING_APPLICATION_JSON={\"application\": {\"a\": \"\", \"b\": \"\", \"c\": \"\", \"m\": \"\"}}"})
@SpringBootTest
public class ApplicationPropertiesWithEmptyStringsTests {
    private final ApplicationProperties applicationProperties;

    @Autowired
    public ApplicationPropertiesWithEmptyStringsTests(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Test
    public void testA() {
        assertEquals("", applicationProperties.getA());
    }

    @Test
    public void testB() {
        assertEquals(true, CollectionUtils.isEmpty(applicationProperties.getB()));
    }

    @Test
    public void testC() {
        assertEquals(true, CollectionUtils.isEmpty(applicationProperties.getC()));
    }

    @Test
    public void testM() {
        assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getM()));
    }
}
