package main.java.view;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;

import java.io.InputStream;
import java.io.IOException;

public abstract class WindowSuperclass extends JFrame implements IView {

	/*
	 * This superclass should be used for all settings
	 * regarding the design of the application.
	 * Views that create a separate window should 
	 * extend this class instead of JFrame to 
	 * automatically inherit all setted options.
	 */
	
	Font dinNeuzeitGrotesk_regular;
	
	public WindowSuperclass() {
		
		// Load & register font
	    InputStream is = WindowSuperclass.class.getResourceAsStream("/main/resources/font/DINNeuzeitGrotesk-BoldCond-Regular.ttf");
		try {
			dinNeuzeitGrotesk_regular = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		setFont(dinNeuzeitGrotesk_regular.deriveFont(12.0f));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TimerView.class.getResource("/main/resources/img/icons/qtproject_placeholder.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: Set to hide when application final, and overwrite main window
		
	}
	

}
