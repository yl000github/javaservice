package swing.operation.event;

public class MouseWheel extends AMouse{
	String scrollType;
	int scrollAmount;
	int wheelRotation;
	public MouseWheel(){
		flag="NATIVE_MOUSE_WHEEL";
	}
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		scrollType=getValue(msg, "scrollType");
		scrollAmount=Integer.parseInt(getValue(msg, "scrollAmount"));
		wheelRotation=Integer.parseInt(getValue(msg, "wheelRotation"));
		robot.mouseWheel(wheelRotation);
//		pause();
		return true;
	}

}
