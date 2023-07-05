package servlet;

import pojo.User;
import dao.User_dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/User/index")//处理业务对接
public class User_servlet extends BasicServlet {
    User model;
    User_dao dao = new User_dao();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User u = dao.login(model);
        String base=req.getContextPath();
        /*List<User> list = DBHelper.executeQuery("select * from user where nike=? and pass=?",
                User.class, model.getNike(), model.getPass());*/
//        if(list.size()==1){
        if(u!=null){
            //可以进入
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(5000);
            session.setAttribute("user",u);
            resp.sendRedirect(base+"/index.jsp");
        }else resp.sendRedirect(base+"/login.html");
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().removeAttribute("user");
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
    @Override
    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<User> list=DBHelper.executeQuery("select * from user",User.class);
        //req.setAttribute("list",list);
        req.setAttribute("list",dao.select());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("powers",User.powers);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*List<User> list=DBHelper.executeQuery("select * from user where id=?",
                User.class,model.getId());
        req.setAttribute("info",list.get(0));*/
        req.setAttribute("info",dao.selectById(model.getId()));
        add(req,resp);
    }
    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*DBHelper.excuteUpdate("insert into user(nike,pass,power)" +
                        " values(?,?,?)",model.getNike(),model.getPass(),model.getPower());
*/
        model.setPass("123");
        dao.insert(model);
        resp.sendRedirect("index");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*DBHelper.excuteUpdate("update user set nike=?,power=? where id=?" +
                        " values(?,?,?)",model.getNike(),model.getPower(),model.getId());
*/
        dao.update(model);
        resp.sendRedirect("index");
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        dao.delete(model.getId());
        resp.sendRedirect("index");
    }
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("login".equals(req.getParameter("cmd"))){//登陆操作
            List<User> list = DBHelper.executeQuery("select * from user where nike=? and pass=?",
                    User.class, req.getParameter("nike"), req.getParameter("pass"));
            if(list.size()==1){
                //可以进入
                resp.sendRedirect("/index1.jsp");
            }else resp.sendRedirect("/login.jsp");
        }else if("add".equals(req.getParameter("cmd"))){//进入新增页
            req.getRequestDispatcher("User/edit.jsp");
        }else if("insert".equals(req.getParameter("cmd"))){//新增保存
            DBHelper.excuteUpdate("insert into user(nike,pass,power)" +
                    " values(?,?,?)",req.getParameter("nike"),req.getParameter("pass"),
                    req.getParameter("power"));
            req.getRequestDispatcher("User/index1.jsp");
        }else if("edit".equals(req.getParameter("cmd"))){//进入修改页
            List<User> list=DBHelper.executeQuery("select * from user where id=?",
                    User.class,req.getParameter("id"));
            req.setAttribute("info",list.get(0));
            req.getRequestDispatcher("User/edit.jsp");
        }else if("update".equals(req.getParameter("cmd"))){//修改保存
            DBHelper.excuteUpdate("update user set nike=?,power=? where id=?" +
                            " values(?,?,?)",req.getParameter("nike"),req.getParameter("power"),
                    req.getParameter("id"));
            req.getRequestDispatcher("User/index1.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }*/
}
