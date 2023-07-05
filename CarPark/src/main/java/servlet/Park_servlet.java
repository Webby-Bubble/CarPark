package servlet;

import dao.Park_dao;
import dao.PayRecord_dao;
import pojo.Park;
import pojo.PayRecord;
import pojo.User;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@WebServlet("/Park/index")//处理业务对接
public class Park_servlet extends BasicServlet {
    Park model;
    Park_dao dao = new Park_dao();
    PayRecord_dao pdao= new PayRecord_dao();
    protected void leave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        // 是否已经停入
        if(model.getCarcode()!=null&&model.getCarcode().length()>0){
            List<Park> has=dao.select("where status=1 and carcode='"+model.getCarcode()+"'");
            if(has.size()>0){
                //车离开,设空车位
                Park en=has.get(0);
                //添加离开收款记录
                PayRecord r = new PayRecord();
                r.setDate(DateUtils.now());
                r.setCarcode(en.getCarcode());
                User current=(User)req.getSession().getAttribute("user");
                r.setUser_id(current.getId());
                long time = 0;
                time=new Date().getTime()-DateUtils.totime(en.getEnterdate());
                r.setTime(time);
                r.setAmount(time/1000/0.5);
                pdao.insert(r);
                //清空停车位
                en.setStatus(0);
                en.setEnterdate("");
                en.setCarcode("");
                dao.updateStatus(en);
            }else{
                req.setAttribute("carcode",model.getCarcode());
                req.setAttribute("error","未找到该车");


            }
        }
        String where="order by date desc";
        req.setAttribute("list",pdao.select(where));
        req.getRequestDispatcher("leave.jsp").forward(req,resp);
    }
    protected void enter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //空车位
        List<Park> isnull=dao.select(" where status=0");
        if(isnull.size()==0){
            req.setAttribute("error","无空停车位");
        }
        // 已经停入
        if(model.getCarcode()!=null&&model.getCarcode().length()>0){
            List<Park> has=dao.select("where status=1 and carcode='"+model.getCarcode()+"'");
            if(has.size()>0){
                req.setAttribute("carcode",model.getCarcode());
                req.setAttribute("error","车辆已经停入-"+has.get(0).getCode());

            }else{
                //车停入,找空车位
                Park en=isnull.get(0);
                en.setStatus(1);
                en.setEnterdate(DateUtils.now());
                en.setCarcode(model.getCarcode());
                dao.updateStatus(en);
            }
        }
        String where=" where status=1 order by enterdate desc";
        req.setAttribute("list",dao.select(where));
        req.getRequestDispatcher("enter.jsp").forward(req,resp);
    }
    @Override
    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list",dao.select());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("powers",Park.statuss);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("info",dao.selectById(model.getId()));
        add(req,resp);
    }
    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        model.setCarcode("");
        model.setEnterdate("");
        model.setStatus(0);
        dao.insert(model);
        resp.sendRedirect("index");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*DBHelper.excuteUpdate("update Park set nike=?,power=? where id=?" +
                        " values(?,?,?)",model.getNike(),model.getPower(),model.getId());
*/
        dao.update(model);
        resp.sendRedirect("index");
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        dao.delete(model.getId());
        resp.sendRedirect("index");
    }
}
