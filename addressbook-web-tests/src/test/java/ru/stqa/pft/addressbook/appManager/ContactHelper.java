package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;

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
        NavigationHelper nh = new NavigationHelper(wd);
        nh.groupPage();
        gh.create(new GroupData().withName(contactData.getGroup()));
        nh.addContactPage();
        selectDDM(By.name("new_group"), contactData.getGroup());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("home"), contactData.getHomePhoneNumber());
    type(By.name("mobile"), contactData.getMobPhoneNumber());
    type(By.name("work"), contactData.getWorkPhoneNumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());
  }

  private boolean isThereAGroup(ContactData contactData) {
    return isGroupPresent(By.name("new_group"), contactData.getGroup());
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }


  public void create(ContactData contact) {
    NavigationHelper nh = new NavigationHelper(wd);
    nh.addContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    nh.homePage();
  }

  public void delete(ContactData Contact) {
    selectContactById(Contact.getId());
    deleteSelectedContact();
    contactCache = null;
    NavigationHelper nh = new NavigationHelper(wd);
    nh.homePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    NavigationHelper nh = new NavigationHelper(wd);
    nh.homePage();
  }
  private Contacts contactCache = null;
  public Contacts all() {
    if(contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withEAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
  }
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
            withHomePhoneNumber(home).withMobPhoneNumber(mobile).withWorkPhoneNumber(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
