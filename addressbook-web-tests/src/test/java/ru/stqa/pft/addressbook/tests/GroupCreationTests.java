package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test2", "test3", "test4"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
