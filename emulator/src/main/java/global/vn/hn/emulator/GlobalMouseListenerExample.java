package global.vn.hn.emulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListenerExample implements NativeMouseInputListener {

	// int valueClick;//click chuot dup hay don
	int valueButton;// chuot nao duoc bam

	public void nativeMouseClicked(NativeMouseEvent e) {
		System.out.println("Mouse Clicked: " + e.getClickCount());
		writeFile(2, valueButton, e.getClickCount());
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		System.out.println("Mouse Pressed: " + e.getButton());
		valueButton = e.getButton();
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		// System.out.println("Mouse Released: " + e.getButton());

	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		// System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
		writeFile(3, e.getX(), e.getY());
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		// System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}

	public void writeFile(int type, int x, int y) {
		try {
			FileWriter fw = new FileWriter("inputMouse.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			Date d = new Date();
			bw.write(date.format(d));
			bw.write("\n");
			bw.write(Integer.toString(type));
			bw.write("\n");
			bw.write(Integer.toString(x));
			bw.write("\n");
			bw.write(Integer.toString(y));
			bw.write("\n");
			bw.close();
			fw.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File("inputMouse.txt");
			if (file.exists()) {
				file.delete();
			}
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		// Construct the example object.
		GlobalMouseListenerExample example = new GlobalMouseListenerExample();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(example);
		GlobalScreen.addNativeMouseMotionListener(example);
	}
}
