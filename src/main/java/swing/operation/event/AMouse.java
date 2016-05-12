package swing.operation.event;

import java.awt.event.MouseEvent;

import exception.InfoException;

public class AMouse extends AEvent{
	int x,y;
	int clickCount;
	int button;
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		clickCount=Integer.parseInt(getValue(msg, "clickCount"));
		button=Integer.parseInt(getValue(msg, "button"));
		int[]pos=getPosition(msg);
		x=pos[0];
		y=pos[1];
		return true;
	}
	protected int buttonMapping(int c) throws InfoException{
		int r;
		switch (c) {
		case 1:
			//left button
			r=MouseEvent.BUTTON1_MASK;
			break;
		case 2:
			//right button
			r=MouseEvent.BUTTON3_MASK;
			break;
		case 3:
			//middle button
			r=MouseEvent.BUTTON2_MASK;
			break;
		default:
			throw new InfoException("buttonMapping 不正常的值");
		}
		return r;
	}
}
