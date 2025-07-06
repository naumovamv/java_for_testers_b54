package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void canCreateContactTest() {
    int contactCount = app.contacts().getContactCount();
    app.contacts().createContact(new ContactData("First name", "Last name", "+7(888)7776655"));
    int newContactCount = app.contacts().getContactCount();
    Assertions.assertEquals(contactCount +1, newContactCount);
  }

  @Test
  public void canCreateGroupWithEmptyNameTest() {
    app.contacts().createContact(new ContactData());
  }

  @Test
  public void canCreateGroupWithNameOnlyTest() {
    app.contacts().createContact(new ContactData().withName("Name", "+7(555)4443322"));
  }
}
