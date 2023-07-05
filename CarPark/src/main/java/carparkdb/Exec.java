package carparkdb;

import dao.DBHelper;
import pojo.Park;
import pojo.PayRecord;
import pojo.User;
import utils.DateUtils;

import java.util.List;
import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean logined=true;
        Store store = new Store();
        //store.init(20);
        int status = -1;
        while(status<0) {
            System.out.println("请输入用户：");
            String nike = in.next();
            System.out.println("请输入密码：");
            String pass = in.next();
            status = store.login(nike, pass);
            if (status < 0) {
                System.out.println("用户名或密码错误！");
            }
        }

        while(status>=0){

            System.out.println("**************************");
            System.out.println("**************************");
            System.out.println("**********停车场管理********");
            System.out.println("**************************");
            System.out.println("*1.停车");
            System.out.println("*2.取车");
            System.out.println("*3.查看停车位");
            System.out.println("*4.收入统计");
            if(status==1) System.out.println("*0.用户管理");
            System.out.print("请输入选择（0-4）：");
            int mark = in.nextInt();
            switch (mark){
                case 1: {
                    System.out.print("请输入车牌号：");
                    String carcode = in.next();
                    int m = store.enter(carcode);
                    if (m > 0) {
                        System.out.println("车辆（" + carcode + "）停入成功！");
                    } else System.out.println("无空闲停车位");

                } break;
                case 2:{
                    System.out.print("请输入待取车牌号：");
                    String carcode = in.next();
                    int m = store.leave(carcode);
                    if (m > 0) {
                        System.out.println("车辆（" + carcode + "）取车成功！");
                    } else System.out.println("无此车");
                }
                    break;
                case 3: {
                    List<Park> list = DBHelper.executeQuery("select * from Park", Park.class);
                    for (Park r : list) {
                        System.out.println(r.getCode() +
                                (r.getStatus() == 1 ? (" :" + r.getCarcode() + "  " + r.getEnterdate()) : "空闲"));
                    }
                }
                    break;
                case 4:
                    List<PayRecord> list = DBHelper.executeQuery("select * from payrecord",PayRecord.class);
                    for(PayRecord r:list){
                        System.out.println(r.getDate()+" "+r.getCarcode()+"     "+ DateUtils.getTimeFormatValue(r.getTime())+" "+
                                r.getAmount()+"元");
                    }
                    break;
                case 0:
                    User u = new User();
                    System.out.println("请输入用户：");
                    u.setNike(in.next());
                    System.out.println("请输入密码：");
                    u.setPass(in.next());
                    store.addUsers(u);
                    System.out.println("添加用户成功");
                    break;
                default:
                    System.out.println("您的输入有误，请重新输入");
            }
            System.out.println("是否退出？（y/n）");
            if(in.next().equalsIgnoreCase("y")){
                System.exit(0);
            }
        }

    }
}
