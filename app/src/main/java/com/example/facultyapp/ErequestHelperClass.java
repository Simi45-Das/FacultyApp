package com.example.facultyapp;

public class ErequestHelperClass {

     String userName,department,IDnumber,phoneNumber;

    public ErequestHelperClass (){

    }

    public ErequestHelperClass (String UserName,String Department,String IDNumber,String PhoneNumber){

        userName = UserName;
        department = Department;
        IDnumber = IDNumber;
        phoneNumber = PhoneNumber;

    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String UserName)
    {
        userName = UserName;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String Department)
    {
        department = Department;
    }

    public String getIDnumber() {
        return IDnumber;
    }
    public void setIDnumber(String IDNumber)
    {
        IDnumber = IDNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber)
    {
        phoneNumber = PhoneNumber;
    }

}
