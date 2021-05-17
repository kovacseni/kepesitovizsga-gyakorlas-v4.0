package hu.nive.ujratervezes.kepesitovizsga.applicants;

import java.util.Objects;

public class Applicant {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String code;
    private String skill;

    public Applicant(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Applicant(String firstName, String lastName, String phoneNumber, String email) {
        this(firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Applicant(String firstName, String lastName, String skill) {
        this(firstName, lastName);
        this.skill = skill;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getSkill() {
        return skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(firstName, applicant.firstName) && Objects.equals(lastName, applicant.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
