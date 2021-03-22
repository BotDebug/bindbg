package dev.binclub.bindbg.util;

import java.awt.*;

public class SwingUtils {
	public static void putWindowInMouseScreen(Window window, int height) {
		var activeScreen = SwingUtils.findActiveScreen();
		if (activeScreen != null) {
			window.setBounds(activeScreen);
			float aspect = 1920f / 1080f;
			window.setSize((int) (height * aspect), height);
		}
	}
	
	public static Rectangle findActiveScreen() {
		return screenAtPoint(MouseInfo.getPointerInfo().getLocation());
	}
	
	public static Rectangle screenAtPoint(Point point) {
		var screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getScreenDevices();
		
		for (GraphicsDevice device : screenDevices) {
			var bounds = device.getDefaultConfiguration().getBounds();
			if (bounds.contains(point)) {
				return bounds;
			}
		}
		
		if (screenDevices.length > 0) {
			// First available monitor
			return screenDevices[0].getDefaultConfiguration().getBounds();
		}
		return null;
	}
}
