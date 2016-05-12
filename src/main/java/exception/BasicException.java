package exception;

public class BasicException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8313246322657600691L;
	public BasicException(String msg){
		super(msg);
	}
	public String hint(){
		String location=this.getStackTrace()[0].toString();
		return this.toString()+"\n"+location;
	}
}
