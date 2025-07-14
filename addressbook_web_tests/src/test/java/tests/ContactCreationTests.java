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

  @Test
  public void canCreateMultipleContactTest() {
    int n = 5;
    int contactCount = app.contacts().getContactCount();

    for (int i =0; i < n; i++) {
      app.contacts().createContact(new ContactData(randomString(i * 5), randomString(i * 8), "+7(888)7776655"));
    }

    int newContactCount = app.contacts().getContactCount();
    Assertions.assertEquals(contactCount + n, newContactCount);
  }
}
