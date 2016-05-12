package swing.operation.event;

public abstract class AMousePRC extends AMouse{
	String modifiers;
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
//		try {
//			//这个字段有不稳定的特性，时有时无,因为没什么用，我就先注释了
//			modifiers=getValue(msg, "modifiers");
//		} catch (Exception e) {
//			System.out.println(this.getClass().getName()+"无法获取modifiers");
//		}
		return true;
	}
}
