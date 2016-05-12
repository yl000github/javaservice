package swing.operation.event;


public class MousePress  extends AMousePRC{
	public static boolean isPress=false;
	public MousePress(){
		flag="NATIVE_MOUSE_PRESSED";
	}
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		//如果已经点击了就不重复触发
		if(!isPress){
			robot.mousePress(buttonMapping(button));
			isPress=true;
		}
//		pause(200);
		return true;
	}
}
