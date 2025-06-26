package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void canCreateContactTest() {
    app.contacts().createContact(new ContactData("First name", "Last name", "+7(888)7776655"));
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
