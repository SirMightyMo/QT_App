package main.java.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.model.IModel;
import main.java.view.IView;

public class LayoutManager implements IController {
	Font dinNeuzeitGrotesk_regular;

	static Map<String, Font> fonts = new HashMap<>();
	static Map<String, Image> icons = new HashMap<>();

	String[] components = { "Button", "CheckBox", "CheckBoxMenuItem", "ColorChooser", "ComboBox", "DesktopIcon",
			"EditorPane", "FormattedTextField", "Label", "List", "Menu", "MenuBar", "MenuItem", "OptionPane", "Panel",
			"PasswordField", "PopupMenu", "ProgressBar", "RadioButton", "RadioButtonMenuItem", "ScrollPane", "Slider",
			"Spinner", "TabbedPane", "Table", "TableHeader", "TextArea", "TextField", "TextPane", "TitledBorder",
			"ToggleButton", "ToolBar", "ToolTip", "Tree", "Viewport" };
	
	String[] iconPaths = { "/main/resources/img/icons/account-outline.png", "/main/resources/img/icons/calendar-clock.png", 
			"/main/resources/img/icons/folder-outline.png", "/main/resources/img/icons/logout.png", 
			"/main/resources/img/icons/view-dashboard-outline.png",
			"/main/resources/img/images/timeline-dummy.png",
			"/main/resources/img/images/productivity-dummy.png",
			"/main/resources/img/images/timeline-dummy.png"};
	
	public static final String ICON_ACCOUNT = "account-outline.png";
	public static final String ICON_SESSIONS = "calendar-clock.png";
	public static final String ICON_PROJECTS = "folder-outline.png";
	public static final String ICON_DASHBOARD = "view-dashboard-outline.png";
	public static final String ICON_LOGOUT = "logout.png";
	public static final String IMAGE_TIMELINE = "timeline-dummy.png";
	public static final String IMAGE_PRODUCTIVITY = "productivity-dummy.png";

	public LayoutManager() {
		loadFonts();
		loadIcons();
		setupUI();
	}

	private void setupUI() {
		FlatDarkLaf.setup();
		UIManager.put("Button.arc", 999);
		UIManager.put("Component.arc", 999);
		UIManager.put("ProgressBar.arc", 999);
		UIManager.put("TextComponent.arc", 999);

		// Set font for every component
		for (int i = 0; i < components.length; i++) {
			UIManager.put(components[i]+".font", dinNeuzeitGrotesk_regular.deriveFont(16.0f));
			UIManager.put(components[i]+".foreground", Color.WHITE);
		}
		//System.out.println(UIManager.getColor("TextField.background"));
	}
	
	private void loadIcons() {
		for (int i = 0; i < iconPaths.length; i++) {
			try {
				Image img = ImageIO.read(getClass().getResource(iconPaths[i]));
				icons.put(iconPaths[i].split("/")[5], img);
			} catch (Exception ex) {
				System.out.println("Loading icon failed:");
				System.out.println(ex);
			}			
		}
	}
	
	public static ImageIcon getImageIcon(String icon, int width, int height) {
		Image result = null;
		for (String key : icons.keySet()) {
			if (key.equalsIgnoreCase(icon)) {
				result = icons.get(key);
				break;
			}
		}
		Image resizedImg = result.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	
	public static ImageIcon getImageIcon(String icon) {
		Image result = null;
		for (String key : icons.keySet()) {
			if (key.equalsIgnoreCase(icon)) {
				result = icons.get(key);
				break;
			}
		}
		return new ImageIcon(result);
	}	
	
	private void loadFonts() {
		// Load & register font
		InputStream is = LayoutManager.class
				.getResourceAsStream("/main/resources/font/DINNeuzeitGrotesk-BoldCond-Regular.ttf");
		try {
			dinNeuzeitGrotesk_regular = Font.createFont(Font.TRUETYPE_FONT, is);
			fonts.put("dinNeuzeitGrotesk_regular", dinNeuzeitGrotesk_regular); // Put font into HashMap
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// Look for font and return
	public static Font getFont(String font) {
		Font result = null;
		for (String key : fonts.keySet()) {
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
