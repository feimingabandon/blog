package com.zou.pojo;

public class User {

    private int id;             // 用户id
    private String name;        // 用户名
    private String pwd;         // 密码
    private String nickname;    // 昵称
    private int root;           // 权限(0:管理员，1:普通用户)
    private String email;       // 邮箱
    private String head;        // 头像地址
    private String registerTime;// 注册时间
    private String birthday;    // 生日
    private int age;            // 年龄

    public User() {
    }

    public User(int id, String name, String pwd, String nickname, int root, String email, String head, String registerTime, String birthday, int age) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.nickname = nickname;
        this.root = root;
        this.email = email;
        this.head = head;
        this.registerTime = registerTime;
        this.birthday = birthday;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", root=" + root +
                ", email='" + email + '\'' +
                ", head='" + head + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                '}';
    }
}
