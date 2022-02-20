package test.java.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.model.User;

/**
 * Unit Tests for LayoutManager
 * 
 * @author kevin
 *
 */
class LayoutManagerTest {

	LayoutManager lm;

	@BeforeEach
	void setUp() throws Exception {
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		lm = new LayoutManager();
		assertNotNull(lm);
	}

	@Test
	void testGetFont() {
		assertEquals(LayoutManager.getFont("dinNeuzeitGrotesk_regular").deriveFont(16.0f).toString(),
				"java.awt.Font[family=DINNeuzeitGrotesk-BoldCond,name=DINNeuzeitGrotesk-BoldCond,style=plain,size=16]");
	}

}
