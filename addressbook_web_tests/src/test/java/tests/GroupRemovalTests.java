package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {
  @Test
  public void canDeleteGroupTest() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }
    int groupCount = app.groups().getCount();
    app.groups().removeGroup();
    int newGroupCount = app.groups().getCount();
    Assertions.assertEquals(groupCount - 1, newGroupCount);
  }

  @Test
  public void canRemovalAllGroupsAtOnceTest() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }
    app.groups().removalAllGroups();
    Assertions.assertEquals(0, app.groups().getCount());
  }
}
