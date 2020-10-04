package com.example.mad;

public class Supplier {

    private String Name;
    private String Age;
    private String Email;
    private String Phone;
    private  String Nic;
    private String Password;

    public Supplier(){


    }
    public Supplier(String name, String age, String email, String phone, String nic, String password) {
        Name = name;
        Age = age;
        Email = email;
        Phone = phone;
        Nic = nic;
        Password = password;
    }



    public void setName(String name) {
        Name = name;
    }

    public String getName() {

        return Name;
    }



    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }



    public void setEmail(String email) {
        Email = email;
    }
    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



}
