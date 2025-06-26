package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

  @Test
  void canModifyGroup() {
    app.groups().isGroupPresent(app);
    app.groups().modifyGroup(new GroupData().withName("modified name"));
  }
}
