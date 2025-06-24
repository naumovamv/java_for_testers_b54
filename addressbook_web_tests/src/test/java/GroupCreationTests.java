import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroupTest() {
    openGroupPage();
    createGroup(new GroupData("test", "test", "test"));
  }

  @Test
  public void canCreateGroupWithEmptyNameTest() {
    openGroupPage();
    var emptyGroup = new GroupData();
    var groupWithName = emptyGroup.withName("some name");
    createGroup(groupWithName);
  }

  @Test
  public void canCreateGroupWithNameOnlyTest() {
    openGroupPage();
    createGroup(new GroupData().withName("some name"));
  }

  @Test
  public void canDeleteGroupTest() {
    openGroupPage();
    isGroupPresent();
    removeGroup();
  }

}
