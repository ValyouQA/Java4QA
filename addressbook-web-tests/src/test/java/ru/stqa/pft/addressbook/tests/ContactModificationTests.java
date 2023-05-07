package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Petr", "Petrovich", "Petrov", "Petrovich home place", "+73988767575", "Petrovich@mail.ru", "20", "August", "1910", "test1"));
    }
    int before = app.getContactHelper().ContactsCount();
    app.getContactHelper().initContactModification ();
    app.getContactHelper().fillContactForm(new ContactData("Saveliy", null, "Petrov", "Petrovich home place", "+73988767575", "Petrovich@mail.ru", "20", "August", "1910", null), false);
    app.getContactHelper().submitContactModification ();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().ContactsCount();
    Assert.assertEquals(after, before);
  }
}
