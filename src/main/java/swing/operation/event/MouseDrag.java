package swing.operation.event;

public class MouseDrag extends AMouse{
	public MouseDrag(){
		flag="NATIVE_MOUSE_DRAGGED";
	}
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		robot.mouseMove(x, y);
		return true;
	}
}
