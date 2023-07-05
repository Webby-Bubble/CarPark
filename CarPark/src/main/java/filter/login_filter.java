package filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class login_filter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) arg0;
        HttpServletResponse resp = (HttpServletResponse) arg1;
        System.out.println(req.getRequestURI());
        String url = req.getRequestURI();
        if(url.indexOf("login.html")>=0 ||"login".equals(req.getParameter("cmd"))) {
                arg2.doFilter(arg0, arg1);//通过拦截
        }
        else {
            if(req.getSession().getAttribute("user")!=null){
                arg2.doFilter(arg0,arg1);//通过拦截
            }else {
                resp.sendRedirect(req.getContextPath()+"/login.html");
            }
        }
    }
}
