package servlet;

import dao.DBHelper;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/aaa")
public class Test_servlet extends HttpServlet {//功能支持
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("我收到请求了"+req.getParameter("count"));
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("<a href='#' style='color:red'>前端标签</a>");
        List<User> list = DBHelper.executeQuery("select * from user", User.class);
        req.setAttribute("list",list);
        req.getRequestDispatcher("index1.jsp").forward(req,resp);//请求重定向
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
