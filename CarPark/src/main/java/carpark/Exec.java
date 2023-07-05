package carpark;


import java.util.List;
import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {
        DBHelper.excuteUpdate("insert into user(nike,pass,power) values(?,?,?)",
                "admin","123","1");
        List<User> list = DBHelper.executeQuery("select * from user", User.class);
        for(User u:list){
            System.out.println(u.nike);
        }
        Scanner in = new Scanner(System.in);
        boolean logined=true;
        Store store = new Store();
        store.init(20);
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
            System.out.print("请输入选择（1-5）：");
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
                case 3:
                    break;
                case 4:
                    System.out.println("你的累计收入："+store.allamount+"元");
                    break;
                case 0:
                    User u = new User();
                    System.out.println("请输入用户：");
                    u.nike = in.next();
                    System.out.println("请输入密码：");
                    u.pass = in.next();
                    store.addUsers(u);
                    System.out.println("添加用户成功");
                    break;

            }
            System.out.println("是否退出？（y/n）");
            if(in.next().equalsIgnoreCase("y")){

            }
        }

    }
}
