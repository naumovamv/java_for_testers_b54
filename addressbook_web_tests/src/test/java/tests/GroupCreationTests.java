package tests;

import manager.ApplicationManager;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroupTest() {
    TestBase.app.openGroupPage();
    TestBase.app.createGroup(new GroupData("test", "test", "test"));
  }

  @Test
  public void canCreateGroupWithEmptyNameTest() {
    TestBase.app.openGroupPage();
    var emptyGroup = new GroupData();
    var groupWithName = emptyGroup.withName("some name");
    TestBase.app.createGroup(groupWithName);
  }

  @Test
  public void canCreateGroupWithNameOnlyTest() {
    TestBase.app.openGroupPage();
    TestBase.app.createGroup(new GroupData().withName("some name"));
  }

  @Test
  public void canDeleteGroupTest() {
    TestBase.app.openGroupPage();
    TestBase.app.isGroupPresent();
    ApplicationManager.removeGroup();
  }

}
