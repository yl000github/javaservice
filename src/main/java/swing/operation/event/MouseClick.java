package swing.operation.event;

public class MouseClick extends AMousePRC{
	public MouseClick(){
		flag="NATIVE_MOUSE_CLICKED";
	}
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
//		robot.mousePress(buttonMapping(button));
//		robot.mouseRelease(buttonMapping(button));
		return true;
	}
}
