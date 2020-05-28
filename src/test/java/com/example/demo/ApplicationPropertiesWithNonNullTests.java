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
 * - list properties with non empty json arrays [9, 10] and ["ten", "nine"],
 * - map properties with non empty json object {"nine": 9}
 *
 * SPRING_APPLICATION_JSON = {
 *     "application": {
 *         "a": "",
 *         "b": [9,10],
 *         "c": ["ten", "nine"],
 *         "m": {"nine": 9}
 *     }
 * }
 *
 * Expecting                    Actual
 *  a == ""                     a == ""
 *  b == [9,10]                 b == [9,10]
 *  c == ["ten","nine"]         c == ["ten","nine"]
 *  m == {"nine": 9}            m == {"one":1, "two":2, "nine":9}
 *
 */
@ActiveProfiles("test")
@TestPropertySource(properties = {"SPRING_APPLICATION_JSON={\"application\": {\"a\": \"\", \"b\": [9, 10], \"c\": [\"ten\", \"nine\"], \"m\": {\"nine\": 9}}}"})
@SpringBootTest
public class ApplicationPropertiesWithNonNullTests {
    private final ApplicationProperties applicationProperties;

    @Autowired
    public ApplicationPropertiesWithNonNullTests(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Test
    public void testA() {
        assertEquals("", applicationProperties.getA());
    }

    @Test
    public void testB() {
        assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getB()));
        assertEquals(2, applicationProperties.getB().size());
    }

    @Test
    public void testC() {
        assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getC()));
        assertEquals(2, applicationProperties.getC().size());
    }

    @Test
    public void testM() {
        assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getM()));
        assertEquals(true, applicationProperties.getM().containsKey("nine"));
        assertEquals(1, applicationProperties.getM().size());
    }

}
