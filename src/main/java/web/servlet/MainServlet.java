package web.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CmdConsumer;
import web.message.ResponseMsg;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CmdConsumer consumer;   
    public MainServlet() {
        super();
        consumer=new CmdConsumer();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data=request.getParameter("data");
		if(data==null)  {
			echo(response,new ResponseMsg(0,"未传值",null).toJson());
			return;
		}
		String res;
		try {
			res = consumer.deal(data);
			echo(response,new ResponseMsg(1,null,res).toJson());
		} catch (Exception e) {
			e.printStackTrace();
			echo(response,new ResponseMsg(0,e.getMessage(),null).toJson());
		}
	}
	public void echo(HttpServletResponse response,String msg) throws IOException{
		response.getWriter().append(msg);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String requestCmd=request.getQueryString();
//		System.out.println(requestCmd);
		response.getWriter().append("Served post at: ").append(request.getContextPath());
	}

}
