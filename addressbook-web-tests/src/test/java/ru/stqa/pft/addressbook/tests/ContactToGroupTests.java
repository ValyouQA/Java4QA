package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.io.File;

import static org.testng.AssertJUnit.assertTrue;

public class ContactToGroupTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0 | app.db().groups().size() == 0 | app.db().verifyContactNotInGroup().size() != 0) {
      app.createGroupIfNot();
      File photo = new File("src/test/resources/photo.png");
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Iosif").withLastname("Ayranovich").withPhoto(photo));
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddContactInGroup() {
    ContactData before = app.db().contactWithoutGroup();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().homePage();
    app.contact().selectContactWithoutGroup(before);
    app.contact().selectGroup(group);
    app.contact().addContactToGroup();
    app.goTo().homePage();
    ContactData after = app.db().contactById(before.getId());
    assertTrue(after.getGroups().contains(group));
    verifyContactListInUI();
  }
}