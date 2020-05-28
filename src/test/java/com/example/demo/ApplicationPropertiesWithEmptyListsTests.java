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
 * - string property with null,
 * - list properties with empty json array [],
 * - map with empty json object {}
 *
 * SPRING_APPLICATION_JSON = {
 *     "application": {
 *         "a": null,
 *         "b": [],
 *         "c": [],
 *         "m": {}
 *     }
 * }
 *
 * Expecting                            Actual
 *  a == null                           a == "some string"
 *  b == null | empty array list        b == [1,2,3]
 *  c == null | empty array list        c == ["one","two","three"]
 *  m == null | empty map               m == {"one":1, "two":2}
 *
 */
@ActiveProfiles("test")
@TestPropertySource(properties = {"SPRING_APPLICATION_JSON={\"application\": {\"a\": null, \"b\": [], \"c\": [], \"m\": {}}}"})
@SpringBootTest
public class ApplicationPropertiesWithEmptyListsTests {
    private final ApplicationProperties applicationProperties;

    @Autowired
    public ApplicationPropertiesWithEmptyListsTests(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Test
    public void testA() {
        assertEquals(null, applicationProperties.getA());
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
        assertEquals(true, CollectionUtils.isEmpty(applicationProperties.getM()));
    }
}
