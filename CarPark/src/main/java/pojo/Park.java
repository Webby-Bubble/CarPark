package pojo;

import java.util.Date;

public class Park {//简单java对象主要用于数据承载
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private String  code;
    private String carcode;
    private int status=0;//0 空  1有车  2预定
    private carpark.Car car;
    public static String[] statuss={"空","有车","预定"};
    private String enterdate;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getStatusname(){
        return statuss[status];
    }//规律性录入

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public int getStatus() {
        return this.status;
    }
    public void setStatus(int i) {
        this.status=i;
    }

    public String getCarcode() {
        return carcode;
    }

    public void setCarcode(String carcode) {
        this.carcode = carcode;
    }
}