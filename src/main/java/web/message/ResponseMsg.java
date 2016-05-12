package web.message;

import utils.JsonUtil;

public class ResponseMsg {

	private int code;//0 failure 1 success
	private String msg;
	private Object data;
	public  ResponseMsg(int code,String msg,Object data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson(){
		String s=JsonUtil.ob2json(this);
		return s;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJson();
	}

}
