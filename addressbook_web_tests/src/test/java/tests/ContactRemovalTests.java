package tests;

import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
  @Test
  public void canDeleteContactTest() {
    app.contacts().isContactPresent(app);
    app.contacts().removeContact();
  }
}
