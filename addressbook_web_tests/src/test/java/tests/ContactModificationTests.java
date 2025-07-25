package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

  @Test
  void canModifyContact() {
    if (app.contacts().getContactCount() == 0) {
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", "", "0", ""));
    }
    var oldContacts = app.contacts().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    var testData = new ContactData().withName("modified name");
    app.contacts().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.contacts().getList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.set(index, testData.withId(oldContacts.get(index).id()));
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  void canModifyContactDbAssertTest() {
    if (app.jdbc().getContactList().isEmpty()) {
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", "", "0", ""));
    }
    var oldContacts = app.jdbc().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    var testData = new ContactData().withName("modified name");
    app.contacts().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.jdbc().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.set(index, testData.withId(oldContacts.get(index).id()));
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
  }
}
