package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification ();
    app.getContactHelper().fillContactForm(new ContactData("Saveliy", null, "Petrov", "Petrovich home place", "+73988767575", "Petrovich@mail.ru", "20", "August", "1910"));
    app.getContactHelper().submitContactModification ();
  }
}
