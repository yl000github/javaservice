package swing.operation.event;

public class KeyRelease  extends AKey{
	public KeyRelease(){
		flag="NATIVE_KEY_RELEASED";
	}

	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		robot.keyRelease(keymapping());
//		pause(200);
		return true;
	}
}
