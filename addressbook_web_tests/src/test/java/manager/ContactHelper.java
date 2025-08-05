package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager manager) {
    super(manager);
  }

  public void createContact(ContactData contact) {
    openContactsPage();
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    returnToHomePage();
  }
  public void createContact(ContactData contact, GroupData group) {
    openContactsPage();
    initContactCreation();
    fillContactForm(contact);
    selectGroup(group);
    submitContactCreation();
    returnToHomePage();
  }

  private void selectGroup(GroupData group) {
    new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
  }

  public void removeContact(ContactData contact) {
    openContactsPage();
    selectContact(contact);
    removeSelectedContacts();
    returnToHomePage();
  }

  private void removeSelectedContacts() {
    click((By.xpath("//input[@value=\'Delete\']")));
  }

  public void openContactsPage() {
    if (!manager.isElementPresent(By.name("searchstring"))) {
      click(By.name("add new"));
    }
  }

  public void isContactPresent(ApplicationManager applicationManager) {
    openContactsPage();
    if (!manager.isElementPresent(By.name("selected[]"))) {
      manager.contacts().createContact(new ContactData());
    }
  }

  private void submitContactCreation() {
    click(By.name("submit"));
  }

  private void initContactCreation() {
    click(By.linkText("add new"));
  }

  private void initContactModification(ContactData contact) {
    WebElement editButton = manager.driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",contact.id())));
    editButton.click();
  }

  private void submitContactModification() {
    click(By.name("update"));
  }

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

  private void fillContactForm(ContactData contact) {
    type(By.name("firstname"), contact.firstName());
    type(By.name("lastname"), contact.lastName());
    type(By.name("mobile"), contact.mobilePhone());
    if (contact.photo() != null && !contact.photo().isBlank()) {
      attach(By.name("photo"), contact.photo());
    }
  }

  private void selectContact(ContactData contact) {
    click(By.cssSelector(String.format("input[value='%s']",contact.id())));
  }

  public int getContactCount() {
    openContactsPage();
    return manager.driver.findElements(By.name("selected[]")).size();
  }

  public void removalAllContacts() {
    openContactsPage();
    selectAllContacts();
    removeSelectedContacts();
  }

  private void selectAllContacts() {
    var checkboxes = manager.driver.findElements(By.name("selected[]"));
    for (var checkbox : checkboxes) {
      checkbox.click();
    }
  }

  public void modifyContact(ContactData contact, ContactData modifiedContact) {
    openContactsPage();
    initContactModification(contact);
    fillContactForm(modifiedContact);
    submitContactModification();
    returnToHomePage();
  }

  public List<ContactData> getList() {
    var contacts = new ArrayList<ContactData>();
    var rows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
    for (var row : rows) {
      var checkbox = row.findElement(By.cssSelector("input[type='checkbox']"));
      var id = checkbox.getAttribute("value");
      var first_name = row.findElements(By.tagName("td")).get(2).getText();
      var last_name = row.findElements(By.tagName("td")).get(1).getText();
      contacts.add(new ContactData().withId(id).withName(first_name).withLastName(last_name));
    }
    return contacts;
  }
}
