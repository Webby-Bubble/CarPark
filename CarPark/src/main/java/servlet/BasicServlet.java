package servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicServlet extends  GenericServlet{
//本类解决了参数获取问题和分派问题 数据库接受包
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Class<? extends BasicServlet> cls=this.getClass();
		//�Զ�������
		Object mod=null;
		try {
			Field sf=cls.getDeclaredField("model");
			sf.setAccessible(true);
			mod=sf.getType().newInstance();
			sf.set(this,mod );
		} catch (Exception e1) {
		}
		
		Enumeration<String> en=req.getParameterNames();
		while(en.hasMoreElements()) {
			String paramname=en.nextElement();
			try {
				setvalues(paramname, this, req);
				
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				if(mod!=null) setvalues(paramname, mod, req);
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
		}
		
		//����cmdִ��ָ������
		String cmd=req.getParameter("cmd");
		if(cmd==null||"select".equals(cmd)) {
			select(req, resp);
			return;
		}
		
		try {//反射
			Method m=cls.getDeclaredMethod(cmd, HttpServletRequest.class,HttpServletResponse.class);
			m.setAccessible(true);
			m.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
			error(req, resp);
		}
		
		
		
		
		
	}
	
	private void setvalues(String paramname,Object o,HttpServletRequest req) {
		Field f;
		try {
			f = o.getClass().getDeclaredField(paramname);
		f.setAccessible(true);
		if(f.getType().equals(int.class)||f.getType().equals(Integer.class)) {
			f.set(o, Integer.valueOf(req.getParameter(paramname).trim()));
		}else if(f.getType().equals(Double.class)||f.getType().equals(double.class)) {
			f.set(o, Double.valueOf(req.getParameter(paramname).trim()));
		}else{
			String p=req.getParameter(paramname);
			f.set(o, new String(p.getBytes("iso-8859-1"),"utf-8"));
		}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	protected void error(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
