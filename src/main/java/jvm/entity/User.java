package jvm.entity;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/30 10:45
 * @Version 1.0
 */
public class User {

    private  long id;
    private String username;

    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
