package swing.operation.event;

public class KeyClick extends AKey{

	public KeyClick(){
		flag="NATIVE_KEY_TYPED";
	}

	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
//		robot.keyPress(rawCode);
//		robot.keyRelease(rawCode);
		return true;
	}

}
