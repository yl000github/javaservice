package swing.operation;

import java.awt.event.WindowEvent;
import java.util.Date;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

import exception.ErrorException;
//import swing.ControlSystem;
import utils.FileUtil;

public class OperationMain extends ABasic{
	Reappear reappear=new Reappear();
	StringBuffer sb=new StringBuffer();
	public Reappear getReappear(){
		return reappear;
	}
	private void store(String msg){ 
//		sb.append(msg+"\n");
		long t;
		try { 
			t=getCurTime();
		} catch (ErrorException e) {
			throw new RuntimeException(e.getMessage());
		}
		msg+=",currentTime="+t;
		FileUtil.writeAdd(path, msg+"\n");
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		shortCut(e);
		store(e.paramString());
	}
	public void shortCut(NativeKeyEvent e){
//		String key=NativeKeyEvent.getKeyText(e.getKeyCode());
//		if(key.equals("F2")){
//			System.out.println("f2");
//			ControlSystem.getHelper().sanGongStart();
//		}else if(key.equals("F4")){
//			System.out.println("f4");
//			ControlSystem.getHelper().sanGongStop();
//		}else if(key.equals("Esc")){
//			System.out.println("esc");
//			try {
//				recordStop();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}else if(key.equals("F4")){
//			
//		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		store(e.paramString());		
	}
	@Override	
	public void nativeMouseClicked(NativeMouseEvent e) {
		store(e.paramString());		
	}
	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void reappearStart() throws Exception {
//		reappear.openFile(ControlSystem.getInstance().getOpenFile());
		reappear.action();
	}

	@Override
	public void reappearStop() throws Exception {
		reappear.stop();
	}
	public static void main(String[] args) {
		OperationMain o=new OperationMain();
		try {
//			Thread.sleep(2000);
			o.recordStart();
			Thread.sleep(50000);
			o.recordStop();
			o.getReappear().setPath(o.path);
//			o.getReappear().setPath("d:/logs/operation 2016-05-11 19-34-16.txt");
			o.reappearStart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
