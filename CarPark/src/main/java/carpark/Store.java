package carpark;

import java.util.ArrayList;

public class Store {
    private ArrayList<Park>	 Parks=new ArrayList<Park>();
    private ArrayList<User> users = new ArrayList<>();
    private User current;//当前操作人
    public double allamount=0;
    public void addUsers(User u){
        users.add(u);
    }
    public int  login(String nike, String pass){
        if(users.size()==0){
            //创建默认用户
            User u = new User();
            u.nike=nike;
            u.pass=pass;
            u.power=1;
            users.add(u);
        }
        //进入
        for(int i = 0;i<users.size();i++){
            if(users.get(i).nike.equals(nike)&&users.get(i).equals(pass)){
                //可以进入
                current=users.get(i);
                //当前操作
                return users.get(i).power;
            }
        }
        return -1;
    }
    public void init(int count) {
        for(int i=0;i<count;i++) {
            Park p = new Park();
            p.setCode("A"+ (i+1));
            Parks.add(p);
        }
    }
    public int enter(String carcode) {
        for(int i=0;i<Parks.size();i++) {
            Car c = new Car();
            c.carcode=carcode;
            int status=Parks.get(i).enter(c);
            if(status==1) {
                return 1;//成功
            }
        }
        return -1;//失败  加入到停车位（有空）
    }
    public int leave(String carcode) {
        //找到车
        //车位空闲
        for(int i=0;i<Parks.size();i++) {
            long time=Parks.get(i).leave(carcode);
            if(time>0) {
                //成功
                //时间计算
                double amount=time/60000;//分钟
                //收钱
                allamount+=amount;
                return 1;
            }
        }
        return -1;//未找到车
    }

}