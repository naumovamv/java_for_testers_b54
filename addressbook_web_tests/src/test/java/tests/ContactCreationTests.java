package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {


  public static List<ContactData> contactProvider() {
    var result = new ArrayList<ContactData>();
    for (var first_name : List.of("", "First name")) {
      for (var last_name : List.of("", "Last name")) {
        for (var mobile_phone : List.of("", "+7(555)4443322")) {
          result.add(new ContactData().withName(first_name).withLastName(last_name).withMobilePhone(mobile_phone));
        }
      }
    }
    for (int i = 0; i < 5; i++) {
      result.add(new ContactData()
              .withName(randomString(i * 10))
              .withLastName(randomString(i * 10))
              .withMobilePhone(randomString(i * 10)));
    }
    return result;
  }

  public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(
            new ContactData("", "contact name'", "", "")));
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContactTest(ContactData contact) {
    app.contacts().openContactsPage();
    List<ContactData> oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    List<ContactData> newContacts = app.contacts().getList();
    Assertions.assertEquals(newContacts.size(), oldContacts.size() + 1);
  }

  @ParameterizedTest
  @MethodSource("negativeContactProvider")
  public void canNotCreateMultipleContactTest(ContactData contact) {
    int contactCount = app.contacts().getContactCount();
    app.contacts().createContact(contact);
    int newContactCount = app.contacts().getContactCount();
    Assertions.assertEquals(contactCount, newContactCount);
  }
}
