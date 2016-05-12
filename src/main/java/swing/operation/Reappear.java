package swing.operation;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import exception.ErrorException;
import exception.InfoException;
import swing.operation.event.*;

public class Reappear{
	IConsume [] consumers;
	public Reappear() {
		consumers=new IConsume[]{
				new MousePress(),new MouseRelease(),	
				new MouseMove(),new MouseWheel(),	
				new MouseClick(),new MouseDrag(),
			new KeyPress(),new KeyRelease(),	
			new KeyClick(),new Command()
		};
	}
	Date startTime;
	protected long getCurTime() throws ErrorException{
		if(startTime==null) throw new ErrorException("起始时间未初始化");
		return new Date().getTime()-startTime.getTime();
	}
	String path;
	public void setPath(String path){
		this.path=path;
	}
	private String openChooseFile(){
		// 创建文件选择器
		JFileChooser fileChooser = new JFileChooser();
		// 设置当前目录
		fileChooser.setCurrentDirectory(new File("d:/logs"));
		fileChooser.setAcceptAllFileFilterUsed(false);
//		final String[][] fileENames = { { ".java", "JAVA源程序 文件(*.java)" }, { ".doc", "MS-Word 2003 文件(*.doc)" },
//				{ ".xls", "MS-Excel 2003 文件(*.xls)" } };
		final String[][] fileENames = { { ".txt", "文本文件(*.txt)" } };
		// 显示所有文件
		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
			public String getDescription() {
				return "所有文件(*.*)";
			}
		});
		// 循环添加需要显示的文件
		for (final String[] fileEName : fileENames) {
			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
						return true;
					}
					return false;
				}
				public String getDescription() {
					return fileEName[1];
				}
			});
		}
		fileChooser.showDialog(null, null);
		return fileChooser.getSelectedFile().getPath();
	}
	public void openFile(String path) throws Exception{
		if(path==null){
			this.path=openChooseFile();
		}else{
			this.path=path;
		}
	}
	//因为模拟恐怕要有少许停顿
	private void pause(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}
	List<String> lines=new ArrayList<>();
	public static int status=0;//当前line的状态，0正常，1处于循环开始，2循环结束
	public static int loopCount=0;
	String loopStartTime;
	int loopStart=-1;
	int loopEnd=-1;
	//耗时任务,新增循环控制
	public void action() throws Exception{
		lines.clear();
		startTime=new Date(); 
		Reader r=new FileReader(path);
		BufferedReader reader=new BufferedReader(r);
		String msg;
		while((msg=reader.readLine())!=null){
			lines.add(msg);
		}
		reader.close();
		for (int i = 0; i < lines.size(); i++) {
			String line=lines.get(i);
			try { 
				consumeLine(line);
				switch (status) {
				case 0:
					break;
				case 1:
					loopStart=i;
					status=0;
					System.out.println("loopStart:"+loopStart+" loopCount:"+loopCount);
					break;
				case 2:
					loopEnd=i;
					System.out.println("loopEnd:"+loopEnd);
					if(loopCount-->0){
						System.out.println("loopStart:"+loopStart+" loopCount:"+loopCount);
						i=loopStart+1;
						status=0;
					}
					break;
				default:
					break;
				}
			} catch (Exception e) {
				//有问题的行先仅输出
				e.printStackTrace();
			}
		}
	}
	public boolean consumeLine(String msg) throws Exception{
		if(status==1){
			loopStartTime=String.valueOf(getCurTime());
		}else if(status==2){
			//刷新当前为最新时间，且加上偏移
			startTime=new Date(new Date().getTime()+Long.parseLong(loopStartTime));
		} 
		String time;
		try {
			time=getValue(msg,"currentTime");
		} catch (Exception e) {
			time=String.valueOf(getCurTime());
		}
		Long t=Long.parseLong(time);
		long cur=getCurTime();
		if(t>cur){
			Thread.sleep(t-cur);
		}else{ 
			startTime=new Date(startTime.getTime()+cur-t);
		}
		for (int i = 0; i < consumers.length; i++) {
			try {
				if(consumers[i].consume(msg)) return true;
			} catch (Exception e) {
				//有问题，继续,罗列原因
			}
		}
		throw new ErrorException("该行未被处理："+msg);
	}
	public String getValue(String msg,String key) throws InfoException{
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
	public void stop() {
		
	}
}
