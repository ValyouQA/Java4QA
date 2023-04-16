package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.goToContactPage();
    app.fillContactForm(new ContactData("Petr", "Petrovich", "Petrov", "Petrovich home place", "+73988767575", "Petrovich@mail.ru", "20", "August", "1910"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
