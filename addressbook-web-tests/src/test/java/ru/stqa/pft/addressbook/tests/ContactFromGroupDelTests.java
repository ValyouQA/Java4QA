package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.io.File;

import static org.testng.AssertJUnit.assertFalse;

public class ContactFromGroupDelTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0 || app.db().groups().size() == 0 || app.db().verifyContactInGroup().size() == 0) {
      if (app.db().contacts().size() == 0) {
        File photo = new File("src/test/resources/photo.png");
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstname("Iosif").withLastname("Ayranovich").withPhoto(photo));
        app.goTo().homePage();
      }
      if (app.db().groups().size() == 0) {
        app.createGroupIfNot();
      }
      if (app.db().verifyContactInGroup().size() == 0) {
        ContactData before = app.db().contactWithoutGroup();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().selectContactWithoutGroup(before);
        app.contact().selectGroup(group);
        app.contact().addContactToGroup();
        app.goTo().homePage();
      }
    }
  }

  @Test
  public void testDeletionContactFromGroup(){
    ContactData before = app.db().contactWithGroup();
    GroupData group = before.getGroups().iterator().next();
    app.goTo().homePage();
    app.contact().getGroupData(group);
    app.contact().selectContact(before);
    app.contact().removeContactFromGroup();
    app.goTo().homePage();

    ContactData freshDataContact = app.db().getContactById(before.getId());
    Groups groupsAfterDeletion = freshDataContact.getGroups();
    assertFalse(groupsAfterDeletion.contains(group));

    verifyContactListInUI();
  }
}