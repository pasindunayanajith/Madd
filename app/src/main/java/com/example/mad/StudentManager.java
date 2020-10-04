package com.example.mad;

public class StudentManager {

    private String Namem;
    private String Agem;
    private String Emailm;
    private String Phonem;
    private  String Nicm;
    private String Passwordm;


    public StudentManager(){

    }


    public StudentManager(String name, String age, String email, String phone, String nic, String password) {
        this.Namem=name;
        this.Agem=age;
        this.Emailm=email;
        this.Phonem=phone;
        this.Nicm=nic;
        this.Passwordm=password;
    }

    public String getNamem() {
        return Namem;
    }

    public void setNamem(String namem) {
        Namem = namem;
    }

    public String getAgem() {
        return Agem;
    }

    public void setAgem(String agem) {
        Agem = agem;
    }

    public String getEmailm() {
        return Emailm;
    }

    public void setEmailm(String emailm) {
        Emailm = emailm;
    }

    public String getPhonem() {
        return Phonem;
    }

    public void setPhonem(String phonem) {
        Phonem = phonem;
    }

    public String getNicm() {
        return Nicm;
    }

    public void setNicm(String nicm) {
        Nicm = nicm;
    }

    public String getPasswordm() {
        return Passwordm;
    }

    public void setPasswordm(String passwordm) {
        Passwordm = passwordm;
    }
}

