package tests;

import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {
  @Test
  public void canDeleteGroupTest() {
    app.groups().isGroupPresent(app);
    app.groups().removeGroup();
  }
}
