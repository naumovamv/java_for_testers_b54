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
          result.add(new ContactData(first_name, last_name, mobile_phone));
        }
      }
    }
    for (int i = 0; i < 5; i++) {
      result.add(new ContactData((randomString(i * 10)), (randomString(i * 10)), (randomString(i * 10))));
    }
    return result;
  }

  public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(
            new ContactData("contact name'", "", "")));
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContactTest(ContactData contact) {
    int n = 5;
    int contactCount = app.contacts().getContactCount();
    app.contacts().createContact(contact);
    int newContactCount = app.contacts().getContactCount();
    Assertions.assertEquals(contactCount + 1, newContactCount);
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
