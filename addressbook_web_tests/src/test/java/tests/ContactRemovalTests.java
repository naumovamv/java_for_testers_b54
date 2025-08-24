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
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", "src/test/resources/images/avatar3.png", "0", "", "", "", ""));
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
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", "src/test/resources/images/avatar3.png", "0", "", "", "", ""));
    }
    app.contacts().removalAllContacts();
    Assertions.assertEquals(0, app.contacts().getContactCount());
  }

  @Test
  public void canDeleteContactDbAssertTest() {
    if (app.jdbc().getContactList().isEmpty()) {
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", "src/test/resources/images/avatar3.png", "0", "", "", "", ""));
    }
    var oldContacts = app.jdbc().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().removeContact(oldContacts.get(index));
    var newContacts = app.jdbc().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  public void canRemovalAllContactsAtOnceDbAssertTest() {
    if (app.jdbc().getContactList().isEmpty()) {
      app.contacts().createContact(new ContactData("", "First name", "Last name", "+7(888)7776655", "src/test/resources/images/avatar3.png", "0", "", "", "", ""));
    }
    app.contacts().removalAllContacts();
    Assertions.assertTrue(app.jdbc().getContactList().isEmpty());
  }
}
