package com.example.mad;

import android.widget.ImageView;

public class AdminProfile {

    public String adminAge;
    public String adminEmail;
    public String adminName;
    public String adminPassword;


    public AdminProfile(){

    }

    public AdminProfile(String adminAge, String adminEmail, String adminName,String adminPassword) {
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminEmail = adminEmail;
        this.adminPassword=adminPassword;

    }


    public String getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(String adminAge) {
        this.adminAge = adminAge;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminAEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}



















