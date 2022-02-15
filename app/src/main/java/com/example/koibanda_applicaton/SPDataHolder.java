package com.example.koibanda_applicaton;

public class SPDataHolder
{

    String userName ,address,experience;
    String categorySpinner,genderSpinner,timeSpinner;

    public SPDataHolder(String userName, String address, String experience, String categorySpinner, String genderSpinner, String timeSpinner) {
        this.userName = userName;
        this.address = address;
        this.experience = experience;
        this.categorySpinner = categorySpinner;
        this.genderSpinner = genderSpinner;
        this.timeSpinner = timeSpinner;
    }

    public String getCategorySpinner() {
        return categorySpinner;
    }

    public void setCategorySpinner(String categorySpinner) {
        this.categorySpinner = categorySpinner;
    }

    public String getGenderSpinner() {
        return genderSpinner;
    }

    public void setGenderSpinner(String genderSpinner) {
        this.genderSpinner = genderSpinner;
    }

    public String getTimeSpinner() {
        return timeSpinner;
    }

    public void setTimeSpinner(String timeSpinner) {
        this.timeSpinner = timeSpinner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
