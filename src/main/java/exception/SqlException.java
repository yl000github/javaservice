package exception;

public class SqlException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	public SqlException(String msg){
		super();
		this.msg=msg;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String location=this.getStackTrace()[0].toString();
		return "错误信息："+msg+" location:"+location;
	}
}
