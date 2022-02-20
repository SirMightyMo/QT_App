package main.java.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

public abstract class WindowSuperclass extends JFrame implements IView {

	/**
	 * This superclass should be used for settings needed in views 
	 * creating a JFrame. <br>
	 * Views that create a separate window should extend this class
	 * instead of JFrame to automatically inherit all setted options.
	 */

	private static final long serialVersionUID = 1L;
	Font dinNeuzeitGrotesk_regular;

	public WindowSuperclass() {
		super();
		// Load & register font
		InputStream is = WindowSuperclass.class
				.getResourceAsStream("/main/resources/font/DINNeuzeitGrotesk-BoldCond-Regular.ttf");
		try {
			dinNeuzeitGrotesk_regular = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		setFont(dinNeuzeitGrotesk_regular.deriveFont(12.0f));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TimerView.class.getResource("/main/resources/img/icons/tracker.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: Set to hide when application final, and overwrite main
														// window

	}
}
