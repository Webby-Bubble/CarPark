package carpark;

import java.util.Date;
public class Park {

    private String  code;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    private int status=0;//0 空  1有车  2预定
    private Car car;
    private long enterdate;
    /**
     * 进车
     * @param car
     * @return   1成功  -1已被占用  -2 已被预定
     */
    public int enter(Car car) {
        if(this.status==1) return -1;
        if(this.status==2) return -2;
        this.car=car;
        this.status=1;
        enterdate=new Date().getTime();
        return 1;
    }
    /**
     * ；离开
     * @param carcode
     * @return   -1无车辆  -2 不是该车   >0 时间
     */
    public long leave(String carcode) {
        if(this.status!=1)return -1;
        if(!this.car.carcode.equals(carcode))return -2;
        long  time=new Date().getTime()-enterdate;
        this.car=null;
        this.status=0;
        return time;
    }
}