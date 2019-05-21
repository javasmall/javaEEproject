package com.bigsai.pan.entity;

public class User {

   private String username;
   private String password;
   private String details;
   private  long phone;
   public User(){}
   public  User(String username,String password,String details,long phone)
   {
       this.username=username;
       this.password=password;
       this.details=details;
       this.phone=phone;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
