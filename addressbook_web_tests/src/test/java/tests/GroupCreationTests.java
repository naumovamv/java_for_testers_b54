package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroupTest() {
    int groupCount = app.groups().getCount();
    app.groups().createGroup(new GroupData("test", "test", "test"));
    int newGroupCount = app.groups().getCount();
    Assertions.assertEquals(groupCount +1, newGroupCount);
  }

  @Test
  public void canCreateGroupWithEmptyNameTest() {
    app.groups().createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnlyTest() {
    app.groups().createGroup(new GroupData().withName("some name"));
  }
}
