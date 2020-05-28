package com.example.demo;

import com.example.demo.config.ApplicationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
 * Expecting                    Actual
 *  a == ""                     a == ""
 *  b == [1,2,3]                b == [1,2,3]
 *  c == ["one","two","three"]  c == ["one","two","three"]
 *  m == {"one":1, "two":2}     m == {"one":1, "two":2}
 *
 */
@ActiveProfiles("test")
@SpringBootTest
public class ApplicationPropertiesDefaultsTests {

	private final ApplicationProperties applicationProperties;

	@Autowired
	public ApplicationPropertiesDefaultsTests(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Test
	public void testA() {
		assertEquals("some string", applicationProperties.getA());
	}

	@Test
	public void testB() {
		assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getB()));
		assertEquals(3, applicationProperties.getB().size());
	}

	@Test
	public void testC() {
		assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getC()));
		assertEquals(3, applicationProperties.getC().size());
	}

	@Test
	public void testM() {
		assertEquals(false, CollectionUtils.isEmpty(applicationProperties.getM()));
		assertEquals(2, applicationProperties.getM().size());
	}

}
