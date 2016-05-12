package swing.operation.event;

import java.awt.AWTException;
import java.awt.Robot;

import exception.InfoException;

public abstract class AEvent implements IConsume{
	Robot robot;
	String [] details;
	public AEvent(){
		try {
			robot=new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	protected String flag;
	@Override
	public boolean consume(String msg) throws Exception {
		details=basicAnalyse(msg);
		if(!flag.equals(details[0])) throw new Exception();//抛出异常就表消化失败
		return true;
	}
	public String[] basicAnalyse(String msg){
		return msg.split(",");
	}
	public int[] getPosition(String msg) throws InfoException{
		int[] res=new int[2];
		String pos=msg.substring(msg.indexOf("(")+1,msg.indexOf(")"));
		String []r=pos.split(",");
		if(r.length!=2) throw new InfoException("获取position出错");
		for (int i = 0; i < r.length; i++) {
			res[i]=Integer.parseInt(r[i]);
		}
		return res;
	}
	protected String getValue(String msg,String key) throws InfoException{
		int keyIndex=msg.indexOf(key);
		if(keyIndex==-1) throw new InfoException("找不到"+key);
		int commaIndex=msg.substring(keyIndex).indexOf(",");
		if(commaIndex!=-1){
			//表明不是最后一组
			String value=msg.substring(keyIndex+key.length()+1, keyIndex+commaIndex);
			return value;
		}else{
			//表明是最后一组
			String value=msg.substring(keyIndex+key.length()+1, msg.length());
			return value;
		}
	}
	protected void pause(int t){
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
		}
	}
	protected void pause(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
	}
}
