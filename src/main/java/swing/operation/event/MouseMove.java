package swing.operation.event;

public class MouseMove  extends AMouse{
	public MouseMove(){
		flag="NATIVE_MOUSE_MOVED";
	}
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		robot.mouseMove(x, y);
//		pause(20);
		return true;
	}
}
