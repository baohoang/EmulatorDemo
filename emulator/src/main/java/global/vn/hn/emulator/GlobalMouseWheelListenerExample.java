package global.vn.hn.emulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class GlobalMouseWheelListenerExample implements NativeMouseWheelListener {

	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		try {
			FileWriter fw = new FileWriter("inputWheelMouse.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			Date d = new Date();
			bw.write(date.format(d));
			bw.write("\n");
			bw.write(Integer.toString(1));
			bw.write("\n");
			bw.write(Integer.toString(e.getWheelRotation()));
			bw.write("\n");
			
			bw.close();
			fw.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}


	public static void main(String[] args) throws IOException {
		try {
			File file = new File("inputWheelMouse.txt");
			if(file.exists()){
				file.delete();
			}
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}

		GlobalScreen.addNativeMouseWheelListener(new GlobalMouseWheelListenerExample());
	}
}