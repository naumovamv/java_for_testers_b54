package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {
  @Test
  public void canDeleteGroupTest() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("", "group name", "group header", "group footer", "", ""));
    }
    var oldGroups = app.groups().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());
    app.groups().removeGroup(oldGroups.get(index));
    var newGroups = app.groups().getList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.remove(index);
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  public void canRemovalAllGroupsAtOnceTest() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("", "group name", "group header", "group footer", "", ""));
    }
    app.groups().removalAllGroups();
    Assertions.assertEquals(0, app.groups().getCount());
  }

  @Test
  public void canDeleteGroupDbAssertTest() {
    if (app.jdbc().getGroupList().isEmpty()) {
      app.groups().createGroup(new GroupData("", "group name", "group header", "group footer", "", ""));
    }
    var oldGroups = app.jdbc().getGroupList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());
    app.groups().removeGroup(oldGroups.get(index));
    var newGroups = app.jdbc().getGroupList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.remove(index);
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  public void canRemovalAllGroupsAtOnceDbAssertTest() {
    if (app.jdbc().getGroupList().isEmpty()) {
      app.groups().createGroup(new GroupData("", "group name", "group header", "group footer", "", ""));
    }
    app.groups().removalAllGroups();
    Assertions.assertTrue(app.jdbc().getGroupList().isEmpty());
  }
}
