package ru.stqa.pft.addressbook;

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

  public ContactData(String name, String middlename, String lastname, String address, String homePhoneNumber, String email, String birthDay, String birthMonth, String birthYear) {
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.homePhoneNumber = homePhoneNumber;
    this.email = email;
    this.birthDay = birthDay;
    this.birthMonth = birthMonth;
    this.birthYear = birthYear;
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
}
