package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
  @Test
  public void canDeleteContactTest() {
    if (app.contacts().getContactCount() == 0) {
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", ""));
    }
    var oldContacts = app.contacts().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().removeContact(oldContacts.get(index));
    var newContacts = app.contacts().getList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  public void canRemovalAllContactsAtOnceTest() {
    if (app.contacts().getContactCount() == 0) {
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", ""));
    }
    app.contacts().removalAllContacts();
    Assertions.assertEquals(0, app.contacts().getContactCount());
  }
}
