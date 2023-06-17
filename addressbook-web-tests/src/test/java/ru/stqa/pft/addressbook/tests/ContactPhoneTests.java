package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
//      app.contact().create(new ContactData().withFirstname("Azaryan").withLastname("Pagoyan")
//              .withMobPhoneNumber("+79379990676").withWorkPhoneNumber("355-00-35").withHomePhoneNumber("43 20 30").withGroup("test1"));
      app.contact().create(new ContactData().withFirstname("Azaryan").withLastname("Pagoyan")
              .withMobPhoneNumber("+79379990676").withHomePhoneNumber("43 20 30").withGroup("test1"));
    }
  }

  @Test
  public void testContactTelephone() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhoneNumber(), contact.getMobPhoneNumber(), contact.getWorkPhoneNumber())
            .stream().filter((s) -> ! s.equals("")).map(ContactPhoneTests:: cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}