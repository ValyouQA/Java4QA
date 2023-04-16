package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    goToContactPage();
    fillContactForm(new ContactData("Petr", "Petrovich", "Petrov", "Petrovich home place", "+73988767575", "Petrovich@mail.ru", "20", "August", "1910"));
    submitContactCreation();
    returnToHomePage();
  }

}
