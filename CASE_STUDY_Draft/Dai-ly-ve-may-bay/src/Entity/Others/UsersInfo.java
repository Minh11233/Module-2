package Entity.Others;

import java.io.Serializable;

public class UsersInfo implements Serializable {
    private String name;
    private String email;
    private String address;
    private String dayOfBirth;
    private String phoneNumber;

     private int UserID;
    public UsersInfo(String name, String dayOfBirth, String phoneNumber, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dayOfBirth = dayOfBirth;
        this.phoneNumber = phoneNumber;
        int count = 0;
        this.UserID = ++count;
    }
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String age) {
        this.dayOfBirth = age;
    }

    @Override
    public String toString() {
        return "\n\nTHÔNG TIN KHÁCH HÀNG: " + "\nHọ và tên: " + name
                + "\nNăm sinh: " + dayOfBirth + "\nEmail: " + email + "\nSố điện thoại: "
                + phoneNumber + "\nĐịa chỉ: " + address;
    }
}