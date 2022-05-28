package com.example.myownshop.Model;

public class Users {

    public String username;
    public String userpassword;
    public String usernumber;
    public String useraddress;

    public Users()
    {

    }
    public Users(String username, String userpassword, String usernumber, String useraddress)
    {
        this.username=username;
        this.userpassword=userpassword;
        this.usernumber=usernumber;
        this.useraddress=useraddress;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public Users(String username, String userpassword, String usernumber)
    {
        this.username=username;
        this.userpassword=userpassword;
        this.usernumber=usernumber;
    }
    public Users(String usernumber, String userpassword)
    {
        this.usernumber=usernumber;
        this.userpassword=userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

   public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }
}
