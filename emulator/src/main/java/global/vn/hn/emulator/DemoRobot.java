package global.vn.hn.emulator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DemoRobot {

	Robot robot;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Data> data = new ArrayList();

	public void readData() {
		BufferedReader br = null;
		try {
			String line1, line2, line3, line4;
			br = new BufferedReader(new FileReader("inputKey.txt"));
			while ((line1 = br.readLine()) != null && (line2 = br.readLine()) != null
					&& (line3 = br.readLine()) != null) {
				String moment = line1;
				int type = Integer.parseInt(line2);
				String content = line3;
				data.add(new Data(moment, type, content));
			}
			br.close();

			/*
			 * br = new BufferedReader(new FileReader("inputWheelMouse.txt"));
			 * while ((sCurrentLine = br.readLine()) != null) { String[] tmp =
			 * sCurrentLine.split("-"); String moment = tmp[0]; int type = 1;
			 * String content = tmp[1].split(":")[0]; data.add(new Data(moment,
			 * type, content)); } br.close();
			 */

			br = new BufferedReader(new FileReader("inputMouse.txt"));
			while ((line1 = br.readLine()) != null && (line2 = br.readLine()) != null && (line3 = br.readLine()) != null
					&& (line4 = br.readLine()) != null) {
				String moment = line1;
				int type = Integer.parseInt(line2);
				String content = line3;
				String content2 = line4;
				data.add(new Data(moment, type, content, content2));
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void leftClick() {
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(200);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(200);
	}

	public void rightClick() {
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.delay(200);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
		robot.delay(200);
	}

	public void doubleLeftClick() {
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(50);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(400);
	}

	public void doubleRightClick() {
		robot.mousePress(InputEvent.BUTTON2_MASK);
		robot.mouseRelease(InputEvent.BUTTON2_MASK);
		robot.delay(50);
		robot.mousePress(InputEvent.BUTTON2_MASK);
		robot.mouseRelease(InputEvent.BUTTON2_MASK);
		robot.delay(400);
	}

	public void type(int i) {
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}

	public void type(String s) {
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)
				code = code - 32;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}

	public void startDemo() throws AWTException {
		robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);

		for (Data dt : data) {
			switch (dt.type) {
			case 0:
				if (dt.content.equals("Space")) {
					type(KeyEvent.VK_SPACE);
				} else {
					type(dt.content);
				}
				break;
			case 1:

				break;
			case 2:
				int m = Integer.parseInt(dt.getContent());
				int c = Integer.parseInt(dt.getContent2());
				if (m == 1 && c == 1) {
					leftClick();
				} else if (m == 1 && c == 2) {
					doubleLeftClick();
				} else if (m == 2 && c == 1) {
					rightClick();
				}
				break;
			case 3:
				int x = Integer.parseInt(dt.getContent());
				int y = Integer.parseInt(dt.getContent2());
				robot.mouseMove(x, y);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws AWTException {
		DemoRobot dr = new DemoRobot();
		dr.readData();
		Collections.sort(dr.data);
		dr.data.remove(dr.data.size() - 1);
		for (Data d : dr.data) {
			System.out.println(d.getMoment() + "___" + d.getType() + "___" + d.getContent());
		}
		dr.startDemo();
	}
}
