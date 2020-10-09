package com.stretton.cruddemo;

/**
 *
 *
 * @author Steven Stretton
 */
public class Students {

    private Integer studentID;
    private String firstName;
    private String surname;
    private Integer yearOfStudy;
    private String gender;
    private String email;
    private String contact;
    private String address;

    public Students(Integer studentID, String firstName, String surname, int yearOfStudy, String gender, String email, String contact, String address) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfStudy = yearOfStudy;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
