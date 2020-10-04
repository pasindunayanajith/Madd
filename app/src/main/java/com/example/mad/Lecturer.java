package com.example.mad;

public class Lecturer {
    private String Namel;
    private String Agel;
    private String Emaill;
    private String Phonel;
    private  String Nicl;
    private String Passwordl;


    public Lecturer(){

}

    public Lecturer(String age, String email, String name, String password, String phone, String nic) {
                 this.Namel=name;
        this.Agel=age;
        this.Emaill=email;
        this.Nicl=nic;
        this.Namel=name;
        this.Passwordl=password;
        this.Phonel=phone;

    }

    public String getNamel() {
        return Namel;
    }

    public void setNamel(String namel) {
        Namel = namel;
    }

    public String getAgel() {
        return Agel;
    }

    public void setAgel(String agel) {
        Agel = agel;
    }

    public String getEmaill() {
        return Emaill;
    }

    public void setEmaill(String emaill) {
        Emaill = emaill;
    }

    public String getPhonel() {
        return Phonel;
    }

    public void setPhonel(String phonel) {
        Phonel = phonel;
    }

    public String getNicl() {
        return Nicl;
    }

    public void setNicl(String nicl) {
        Nicl = nicl;
    }

    public String getPasswordl() {
        return Passwordl;
    }

    public void setPasswordl(String passwordl) {
        Passwordl = passwordl;
    }
}
