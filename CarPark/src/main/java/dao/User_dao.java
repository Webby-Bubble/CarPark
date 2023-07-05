package dao;

import pojo.User;

import java.util.List;

public class User_dao {
    public User login(User model){
        List<User> list = DBHelper.executeQuery("select * from user where nike=? and pass=?",User.class,model.getNike(),model.getPass());
        if(list.size()==1)return list.get(0);
        return null;
    }
    public List<User> select(){
        return DBHelper.executeQuery("select * from user", User.class);
    }
    public int insert(User model){
        return DBHelper.excuteUpdate("insert into user(nike,pass,power) values(?,?,?)",model.getNike(),model.getPass(),model.getPower());
    }
    public int update(User model){
        return  DBHelper.excuteUpdate("update user set nike=?,power=? where id=?",model.getNike(),model.getPower(),model.getId());
    }
    public int delete(int id){
        return  DBHelper.excuteUpdate("delete from user where id=?",id);
    }
    public User selectById(int id){
        return DBHelper.executeQuery("select * from user where id=?",User.class,id).get(0);
    }
}
