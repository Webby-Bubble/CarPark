package dao;

import pojo.PayRecord;

import java.util.List;

public class PayRecord_dao {
    public List<PayRecord> select(){
        return DBHelper.executeQuery("select * from PayRecord order by code", PayRecord.class);
    }
    public List<PayRecord> select(String where){
        return DBHelper.executeQuery("select * from PayRecord "+where, PayRecord.class);
    }
    public int insert(PayRecord model){
        return DBHelper.excuteUpdate("insert into PayRecord(date,user_id,carcode,amount,time) values(?,?,?,?,?)",model.getDate(),model.getUser_id(),model.getCarcode(),model.getAmount(),model.getTime());
    }
}
