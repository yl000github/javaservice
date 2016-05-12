package service;

import swing.operation.OperationMain;
import utils.JsonUtil;
import utils.StringUtil;
import web.message.ResponseMsg;

/**
 * 指令结构
 * {name:,param:
 * }
 * record time(default 10s) 返回文件路径
 * reappear path
 * 
 * @author Administrator
 *
 */
public class CmdConsumer {
	OperationMain operation=new OperationMain();
	public String deal(String data) throws Exception {
		System.out.println("传过来的data："+data);
		Instruct instruct=(Instruct) JsonUtil.json2ob(data, Instruct.class);
		if(instruct.getName().equals("record")){
			String time=instruct.getParam();
			if(!StringUtil.checkValid(time)) time="10";
			operation.recordStart();
			int t=Integer.parseInt(time)*1000;
			Thread.sleep(t);
			operation.recordStop();
			String path=operation.getPath();
			return path;
		}
		if(instruct.getName().equals("reappear")){
			String path=instruct.getParam();
			//TODO
			if(!StringUtil.checkValid(path)) path="";
			operation.getReappear().setPath(path);
			operation.reappearStart();
			return "ok";
		}
		return "invalid instruct";
	}

}
