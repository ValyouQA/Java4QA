package ru.stqa.pft.addressbook.models;

import java.util.Objects;

public class ContactData {
  private final String name;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String homePhoneNumber;
  private final String email;
  private final String birthDay;
  private final String birthMonth;
  private final String birthYear;
  private String group;

  public ContactData(String name, String middlename, String lastname, String address, String homePhoneNumber, String email, String birthDay, String birthMonth, String birthYear, String group) {
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.homePhoneNumber = homePhoneNumber;
    this.email = email;
    this.birthDay = birthDay;
    this.birthMonth = birthMonth;
    this.birthYear = birthYear;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhoneNumber() {
    return homePhoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getBirthDay() {
    return birthDay;
  }

  public String getBirthMonth() {
    return birthMonth;
  }

  public String getBirthYear() {
    return birthYear;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
