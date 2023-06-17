package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    if (creation) {
      if (isThereAGroup(contactData)){
        selectDDM(By.name("new_group"), contactData.getGroup());
      } else {
        GroupHelper gh = new GroupHelper(wd);
        gh.create(new GroupData().withName(contactData.getGroup()));
        NavigationHelper nh = new NavigationHelper(wd);
        nh.addContactPage();
        selectDDM(By.name("new_group"), contactData.getGroup());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getHomePhoneNumber());
    type(By.name("email"), contactData.getEmail());
  }

  private boolean isThereAGroup(ContactData contactData) {
    return isGroupPresent(By.name("new_group"), contactData.getGroup());
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }


  public void create(ContactData contact) {
    NavigationHelper nh = new NavigationHelper(wd);
    nh.addContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    nh.homePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    NavigationHelper nh = new NavigationHelper(wd);
    nh.homePage();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    NavigationHelper nh = new NavigationHelper(wd);
    nh.homePage();
  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}
