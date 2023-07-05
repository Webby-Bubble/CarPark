package carpark;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String DRIVER="org.sqlite.JDBC";
    private static final String URL="jdbc:sqlite://C://Users//71478//Documents//park.s3db";
    private static final String USER="";
    private static final String PASSWORD="";
     
    /**
     *连接数据库
     * @return 链接数据库对象
     */
    private static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
     
    /**
     * 释放相应资源
     * @param rs
     * @param pstmt
     * @param conn
     */
    private static void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 此方法可以完成增删改查所有的操作
     * @param sql
     * @param params
     * @return true or false
     * insert into user(name) values(?);
     */
    public static int excuteUpdate(String sql,Object... params){
        int res=-2;//受影响的行数
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);//װ装载sql语句
            if(params!=null){
                //加入？占位符，在执行前把？占位符替换掉
                for(int i=0;i<params.length;i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            res=pstmt.executeUpdate();//执行sql语句
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeAll(rs, pstmt, conn);
        }
        return res;
    }
    /**
     * ʹ�÷��ͷ����ͷ�����ƽ��з�װ
     * @param sql
     * @param params
     * @param cls
     * @return
     */
    public static <T> List<T> executeQuery(String sql,Class<T> cls,Object... params){//变长参数
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;//结果集
        List<T> data=new ArrayList<T>();
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);//装在sql语句
            if(params!=null){
                //加入？占位符，在执行之前把？占位符替换掉
                for(int i=0;i<params.length;i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            rs=pstmt.executeQuery();

            //不是数据库连接池 是对象映射模式
            //把查询出来的记录封装成对应的实体类对象
            ResultSetMetaData rsd=rs.getMetaData();//获得列对象，通过此对象可以得到表的结构，包括，列明
            while(rs.next()){
                T m=cls.newInstance();//运用java反射机制
                for(int i=0;i<rsd.getColumnCount();i++){
                	try {
                    String col_name=rsd.getColumnLabel(i+1);//获得列名
                    Object value=rs.getObject(col_name);//获得列多对应的值
                    Field field=cls.getDeclaredField(col_name);
                    field.setAccessible(true);//给私有书香设置可访问权
                    field.set(m, value);//给对象私有属性赋值
                	}catch (Exception e) {
//                		e.printStackTrace();
//						System.out.println("列处理错误");
					}
                }
                data.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeAll(rs, pstmt, conn);
        }
        return data;
    }
}