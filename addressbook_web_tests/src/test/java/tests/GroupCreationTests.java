package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroupTest() {
    app.groups().createGroup(new GroupData("test", "test", "test"));
  }

  @Test
  public void canCreateGroupWithEmptyNameTest() {
    app.groups().createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnlyTest() {
    app.groups().createGroup(new GroupData().withName("some name"));
  }

  @Test
  public void canDeleteGroupTest() {
    app.groups().isGroupPresent(app);
    app.groups().removeGroup();
  }
}
