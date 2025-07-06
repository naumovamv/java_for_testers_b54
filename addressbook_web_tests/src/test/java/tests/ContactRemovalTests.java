package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
  @Test
  public void canDeleteContactTest() {
    if (app.contacts().getContactCount() == 0) {
      app.contacts().createContact(new ContactData("First name", "Last name", "+7(888)7776655"));
    }
    int contactCount = app.contacts().getContactCount();
    app.contacts().removeContact();
    int newContactCount = app.contacts().getContactCount();
    Assertions.assertEquals(contactCount - 1, newContactCount);
  }

  @Test
  public void canRemovalAllContactsAtOnceTest() {
    if (app.contacts().getContactCount() == 0) {
      app.contacts().createContact(new ContactData("First name", "Last name", "+7(888)7776655"));
    }
    app.contacts().removalAllContacts();
    Assertions.assertEquals(0, app.contacts().getContactCount());
  }
}
