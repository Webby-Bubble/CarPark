package pojo;

public class User {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public static String[] powers={"操作员","管理员"};
    public String getPowername(){
        return powers[power];
    }
    private String nike;
    private String pass;
    private int power = 0; //0管理员 1操作员
}
