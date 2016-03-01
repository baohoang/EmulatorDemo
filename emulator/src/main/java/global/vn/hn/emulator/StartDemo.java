package global.vn.hn.emulator;

import java.io.File;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class StartDemo {
	public static void main(String[] args) {
		try {
			File file = new File("inputKey.txt");
			if (file.exists()) {
				file.delete();
			}
			
			File file1 = new File("inputMouse.txt");
			if(file1.exists()){
				file1.delete();
			}
			
			File file2 = new File("inputWheelMouse.txt");
			if (file2.exists()) {
				file2.delete();
			}
			
			System.out.println(1);
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
		GlobalScreen.addNativeMouseWheelListener(new GlobalMouseWheelListenerExample());

		// Construct the example object.
		GlobalMouseListenerExample example = new GlobalMouseListenerExample();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(example);
		GlobalScreen.addNativeMouseMotionListener(example);
	}
}
