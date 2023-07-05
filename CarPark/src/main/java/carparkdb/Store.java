package carparkdb;

import dao.DBHelper;
import pojo.Park;
import pojo.User;
import utils.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Store {
    private User current;//当前操作人
    double allamount=0;
    public void addUsers(User u){
        DBHelper.excuteUpdate("insert into user(nike,pass,power) values(?,?,?)",
                u.getNike(),u.getPass(),u.getPower());
    }
    public int  login(String nike, String pass){
        List<User> list = DBHelper.executeQuery("select * from user where nike=? and pass=?",
                User.class, nike, pass);
        if(list.size()==1){
            //可以进入
                current=list.get(0);
                //当前操作
                return list.get(0).getPower();
        }else return -1;
    }
    public void init(int count) {
        for(int i=0;i<count;i++) {
            DBHelper.excuteUpdate("insert into park(code,status,carcode,enterdate) values(?,?,?,?)",
                    "A"+ (i+1),0,"","");
        }
    }
    public int enter(String carcode) {
        List<Park> list = DBHelper.executeQuery("select * from park where status = 0 limit 1", Park.class);
        if(list.size()==1){
            DBHelper.excuteUpdate("update park set carcode=?,status=?,enterdate=? where id=?",
                    carcode,1, DateUtils.now(),list.get(0).getId());
            return 1;//成功
        }else return -1;//失败  加入到停车位（有空）
    }
    public int leave(String carcode) {
        //找到车
        //车位空闲
        List<Park> list = DBHelper.executeQuery("select * from park where carcode=?",
                Park.class,carcode);
        if(list.size()==1){
            String  enterdate = list.get(0).getEnterdate();
            long time = 0;
            try {
                time = new Date().getTime()- DateUtils.totime(enterdate);
                if(time>0) {
                    //成功
                    //时间计算
                    double amount=time/1000;//s
                    //收钱
                    allamount+=amount;
                }
            } catch (ParseException e) {
                System.out.println("日期计算错误");
            }
            DBHelper.excuteUpdate("insert into payrecord(date,user_id,carcode,amount,time) values(?,?,?,?,?)",
                    DateUtils.now(),current.getId(),carcode,allamount,time);
            DBHelper.excuteUpdate("update park set status=?,enterdate=?,carcode=? where id=?",
                    0,"","",list.get(0).getId());
            return 1;
        }
        else return -1;//未找到车


    }

}