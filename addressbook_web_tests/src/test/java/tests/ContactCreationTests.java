package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  public static List<ContactData> contactProvider() {
    var result = new ArrayList<ContactData>();
    for (var first_name : List.of("", "First name")) {
      for (var last_name : List.of("", "Last name")) {
        for (var mobile_phone : List.of("", "+7(555)4443322")) {
          for (var photo : List.of("src/test/resources/images/avatar.png"))
          result.add(new ContactData().withName(first_name).withLastName(last_name).withMobilePhone(mobile_phone).withPhoto(photo));
        }
      }
    }
    for (int i = 0; i < 5; i++) {
      result.add(new ContactData()
              .withName(randomString(i * 10))
              .withLastName(randomString(i * 10))
              .withMobilePhone(randomString(i * 10))
              .withPhoto("src/test/resources/images/avatar.png"));
    }
    return result;
  }

  public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(
            new ContactData("", "contact name'", "", "", "")));
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContactTest(ContactData contact) {
    var oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
            .withName(newContacts.get(newContacts.size() - 1).firstName())
            .withLastName(newContacts.get(newContacts.size() - 1).lastName())
            .withMobilePhone(newContacts.get(newContacts.size() - 1).mobilePhone())
            .withPhoto(newContacts.get(newContacts.size() - 1).photo()));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @ParameterizedTest
  @MethodSource("negativeContactProvider")
  public void canNotCreateMultipleContactTest(ContactData contact) {
    var oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    Assertions.assertEquals(newContacts, oldContacts);
  }
}
