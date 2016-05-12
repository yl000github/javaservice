package swing.operation;


public interface IHandle{
	public void recordStart() throws Exception;
	public void recordStop() throws Exception;
	public void reappearStart() throws Exception;
	public void reappearStop() throws Exception;
}
