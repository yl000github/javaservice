package swing.operation;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import exception.ErrorException;
import utils.DateUtil;
import utils.FileUtil;

public abstract class ABasic implements IHook,IHandle{
	String path;
	Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	public ABasic(){
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);
	}
	Date recordStartTime;
	@Override
	public void recordStart() throws Exception {
		path="d:/logs/operation "+DateUtil.getNowFormatD()+".txt";
		FileUtil.createFile(path);
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(this);
		GlobalScreen.addNativeMouseListener(this);
		GlobalScreen.addNativeMouseMotionListener(this);
		GlobalScreen.addNativeMouseWheelListener(this);
		recordStartTime=new Date();
	}
	protected long getCurTime() throws ErrorException{
		if(recordStartTime==null) throw new ErrorException("起始时间为初始化");
		return new Date().getTime()-recordStartTime.getTime();
	}
	@Override
	public void recordStop() throws Exception {
		GlobalScreen.unregisterNativeHook();
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
