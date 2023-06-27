package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      File photo = new File("src/test/resources/photo.png");
      app.contact().create(new ContactData().withFirstname("Petr").withLastname("Petrov").withMobPhoneNumber("+73988767575")
              .withEmail("Petrovich@mail.ru").withPhoto(photo));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/photo.png");
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Albert").withLastname("Azazoev")
            .withMobPhoneNumber("+75535140854").withEmail("Albert@mail.ru").withPhoto(photo);
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
