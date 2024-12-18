package com.example.facultyapp;

public class FregisHelperClass {

    String name,EmailId,Password,IDnumber,id_pass,phone,sub1,sub2,sub3;

    public FregisHelperClass(){

    }

    public FregisHelperClass(String Name,String IDNumber,String Sub1,String Sub2,String Sub3,String emailId, String password,
                             String Phone,String ID_pass){
        name = Name;
        IDnumber = IDNumber;
        EmailId = emailId;
        Password = password;
        phone = Phone;
        id_pass = ID_pass;
        sub1 = Sub1;
        sub2 = Sub2;
        sub3 = Sub3;
    }

    public String getName() {
        return name;
    }
    public void setName(String Name)
    {
        name = Name;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDNumber) {
        IDnumber = IDNumber;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String Phone)
    {
        phone = Phone;
    }

    public String getID_pass() {
        return id_pass;
    }
    public void setID_pass(String ID_pass)
    {
        id_pass = ID_pass;
    }

    public String getSub1() {
        return sub1;
    }
    public void setSub1(String Sub1)
    {
        sub1 = Sub1;
    }

    public String getSub2() {
        return sub2;
    }
    public void setSub2(String Sub2)
    {
        sub2 = Sub2;
    }

    public String getSub3() {
        return sub3;
    }
    public void setSub3(String Sub3)
    {
        sub3 = Sub3;
    }


}


