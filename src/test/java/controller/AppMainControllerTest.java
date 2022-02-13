package test.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppMainControllerTest {
	AccountControllerTest ac;

	@BeforeEach
	void setUp() throws Exception {
		ac = new AccountControllerTest();
	}

	@Test
	void constructorTest() {
		assertNotNull(ac);
	}

	@Test
	void testMouseClicked() {

	}

}
