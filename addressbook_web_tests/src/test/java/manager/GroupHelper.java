package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

  public GroupHelper(ApplicationManager manager) {
    super(manager);
  }

  public void createGroup(GroupData group) {
    openGroupsPage();
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }

  public void modifyGroup(GroupData modifiedGroup) {
    openGroupsPage();
    selectGroup();
    initGroupModification();
    fillGroupForm(modifiedGroup);
    submitGroupModification();
    returnToGroupsPage();
  }

  public void removeGroup() {
    openGroupsPage();
    selectGroup();
    removeSelectedGroups();
    returnToGroupsPage();
  }

  private void removeSelectedGroups() {
    click(By.name("delete"));
  }

  public void openGroupsPage() {
    if (!manager.isElementPresent(By.name("new"))) {
      click(By.linkText("groups"));
    }
  }

  private void submitGroupCreation() {
    click(By.name("submit"));
  }

  private void initGroupCreation() {
    click(By.name("new"));
  }

  private void returnToGroupsPage() {
    click(By.linkText("group page"));
  }

  private void submitGroupModification() {
    click(By.name("update"));
  }

  private void fillGroupForm(GroupData group) {
    type(By.name("group_name"), group.name());
    type(By.name("group_header"), group.header());
    type(By.name("group_footer"), group.footer());
  }

  private void initGroupModification() {
    click(By.name("edit"));
  }

  private void selectGroup() {
    click(By.name("selected[]"));
  }

  public int getCount() {
    openGroupsPage();
    return manager.driver.findElements(By.name("selected[]")).size();
  }

  public void removalAllGroups() {
    openGroupsPage();
    selectAllGroups();
    removeSelectedGroups();
  }

  private void selectAllGroups() {
    var checkboxes = manager.driver.findElements(By.name("selected[]"));
    for (var checkbox: checkboxes){
      checkbox.click();
    }
  }
}
