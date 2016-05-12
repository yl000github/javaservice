package swing.operation.event;

import swing.operation.Reappear;

public class Command implements IConsume{

	/**
	 * 格式
	 * for count
	 * end
	 */
	@Override
	public boolean consume(String msg) throws Exception {
		System.out.println(msg);
		String[] strs=msg.split(" ");
		if(strs[0].equals("for")){
			Reappear.status=1;
//			String count=msg.substring(msg.indexOf(" ")+1,msg.length());
			String count=strs[1];
			Reappear.loopCount=Integer.parseInt(count);
			return true;
		}
		if(strs[0].equals("end")){
			Reappear.status=2;
			return true;
		}
		Reappear.status=0;
		return true;
	}

}
