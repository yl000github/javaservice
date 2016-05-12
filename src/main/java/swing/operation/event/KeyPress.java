package swing.operation.event;


public class KeyPress extends AKey{
	public KeyPress(){
		flag="NATIVE_KEY_PRESSED";
	}

	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		robot.keyPress(keymapping());
//		pause(200);
		return true;
	}

}
