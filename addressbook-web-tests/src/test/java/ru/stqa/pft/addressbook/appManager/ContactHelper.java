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
        gh.createGroup(new GroupData(contactData.getGroup(), null, null));
        NavigationHelper nh = new NavigationHelper(wd);
        nh.goToAddContactPage();
        selectDDM(By.name("new_group"), contactData.getGroup());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhoneNumber());
    type(By.name("email"), contactData.getEmail());
    selectDDM(By.name("bday"), contactData.getBirthDay());
    selectDDM(By.name("bmonth"), contactData.getBirthMonth());
    type(By.name("byear"), contactData.getBirthYear());
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

  public void createContact(ContactData contactData) {
    NavigationHelper nh = new NavigationHelper(wd);
    List<ContactData> before = getContactList();
    nh.goToAddContactPage();
    fillContactForm(contactData, true);
    submitContactCreation();
    nh.goToHomePage();
    List<ContactData> after = getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String name = element.getText();
      String lastname = element.getText();
      ContactData contact = new ContactData(name,  null, lastname, null, null, null, null, null, null, null);
    contacts.add(contact);
    }
    return contacts;
  }

  public int ContactsCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }
}
