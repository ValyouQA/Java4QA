package ru.stqa.pft.addressbook.models;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @Expose
  private String mobPhoneNumber;
  @Expose
  private String workPhoneNumber;
  @Expose
  private String homePhoneNumber;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private String group;
  private String allPhones;
  private String allEmails;
  @Expose
  private String address;
  private File photo;


  public int getId(){
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withMobPhoneNumber(String mobPhoneNumber) {
    this.mobPhoneNumber = mobPhoneNumber;
    return this;
  }
  public ContactData withWorkPhoneNumber(String workPhoneNumber) {
    this.workPhoneNumber = workPhoneNumber;
    return this;
  }
  public ContactData withHomePhoneNumber(String homePhoneNumber) {
    this.homePhoneNumber = homePhoneNumber;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withEAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }
  public File getPhoto() {
    return photo;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobPhoneNumber() {
    return mobPhoneNumber;
  }
  public String getWorkPhoneNumber() {
    return workPhoneNumber;
  }
  public String getHomePhoneNumber() {
    return homePhoneNumber;
  }
  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getAllEmails() {
    return allEmails;
  }

  public String getGroup() {
    return group;
  }
  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
