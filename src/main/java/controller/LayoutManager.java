package main.java.controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.model.IModel;
import main.java.view.IView;

public class LayoutManager implements IController {

	Font dinNeuzeitGrotesk_regular;
	
	static Map<String, Font> fonts = new HashMap<>();
	
	String[] components = {
			"Button",
			"CheckBox",
			"CheckBoxMenuItem",
			"ColorChooser",
			"ComboBox",
			"DesktopIcon",
			"EditorPane",
			"FormattedTextField",
			"Label",
			"List",
			"Menu",
			"MenuBar",
			"MenuItem",
			"OptionPane",
			"Panel",
			"PasswordField",
			"PopupMenu",
			"ProgressBar",
			"RadioButton",
			"RadioButtonMenuItem",
			"ScrollPane",
			"Slider",
			"Spinner",
			"TabbedPane",
			"Table",
			"TableHeader",
			"TextArea",
			"TextField",
			"TextPane",
			"TitledBorder",
			"ToggleButton",
			"ToolBar",
			"ToolTip",
			"Tree",
			"Viewport"
			};
	
	public LayoutManager() {
	
		// Load & register font
	    InputStream is = LayoutManager.class.getResourceAsStream("/main/resources/font/DINNeuzeitGrotesk-BoldCond-Regular.ttf");
		try {
			dinNeuzeitGrotesk_regular = Font.createFont(Font.TRUETYPE_FONT, is);
			fonts.put("dinNeuzeitGrotesk_regular", dinNeuzeitGrotesk_regular); // Put font into HashMap
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		// Set look and feel (FlatLaf Theme)
		FlatDarkLaf.setup();
		// customize components with change of properties (see:
		// https://www.formdev.com/flatlaf/customizing/)
		UIManager.put("Button.arc", 999);
		UIManager.put("Component.arc", 999);
		UIManager.put("ProgressBar.arc", 999);
		UIManager.put("TextComponent.arc", 999);
		
		// Set font for every component
		for (int i = 0; i < components.length; i++) {
			UIManager.put(components[i]+".font", dinNeuzeitGrotesk_regular.deriveFont(14.0f));
			UIManager.put(components[i]+".foreground", Color.WHITE);
		}
		
		
		
	}
	
	// Look for font and return
	public static Font getFont(String font) {
		Font result = null;
		for(String key : fonts.keySet()) {
			if (key.equalsIgnoreCase(font)) {
				result = fonts.get(key);
				break;
			}
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
