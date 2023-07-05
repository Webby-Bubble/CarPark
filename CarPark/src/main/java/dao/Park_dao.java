package dao;

import pojo.Park;

import java.util.List;

public class Park_dao {
    public List<Park> select(){
        return DBHelper.executeQuery("select * from Park order by code", Park.class);
    }
    public List<Park> select(String where){
        return DBHelper.executeQuery("select * from Park "+where, Park.class);
    }
    public int insert(Park model){
        return DBHelper.excuteUpdate("insert into Park(code,status,carcode,enterdate) values(?,?,?,?)",model.getCode(),model.getStatus(),model.getCarcode(),model.getEnterdate());
    }
    public int update(Park model){
        return  DBHelper.excuteUpdate("update Park set code=? where id=?",model.getCode(),model.getId());
    }
    public int updateStatus(Park model){
        return  DBHelper.excuteUpdate("update Park set status=?,carcode=?,enterdate=? where id=?",model.getStatus(),model.getCarcode(),model.getEnterdate(),model.getId());
    }
    public int delete(int id){
        return  DBHelper.excuteUpdate("delete from Park where id=?",id);
    }
    public Park selectById(int id){
        return DBHelper.executeQuery("select * from Park where id=?",Park.class,id).get(0);
    }
}
