package swing.operation;

import java.awt.event.WindowListener;

import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelListener;

public interface IHook extends NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener, WindowListener {

}
