package com.ass2.f190260_i190468.models;

public class Users {
    private String Name, Email, Password, Gender, Phone_Number, profilePic;

    public Users(String Name, String Email, String Phone_Number, String Password, String Gender,String profilePic) {
        this.Name = Name;
        this.Email = Email;
        this.Phone_Number = Phone_Number;
        this.Password = Password;
        this.Gender = Gender;
        this.profilePic = profilePic;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }
    public void setPhone_Number(String phone_Number) {
        this.Phone_Number = phone_Number;;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
