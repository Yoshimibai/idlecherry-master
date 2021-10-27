package com.sydney.entity;

public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String school;
    private String gender;
    private String mobilephone;
    private String profile;
    private String signature;
    private String faculty;
    private Integer startYear;

    public User() {}

    public User(Integer id, String email, String firstName, String lastName, String password, String school, String gender, String mobilephone, String profile, String signature, String faculty, Integer startYear) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.school = school;
        this.gender = gender;
        this.mobilephone = mobilephone;
        this.profile = profile;
        this.signature = signature;
        this.faculty = faculty;
        this.startYear = startYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", school='" + school + '\'' +
                ", gender='" + gender + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", profile='" + profile + '\'' +
                ", signature='" + signature + '\'' +
                ", faculty='" + faculty + '\'' +
                ", startYear=" + startYear +
                '}';
    }
}
