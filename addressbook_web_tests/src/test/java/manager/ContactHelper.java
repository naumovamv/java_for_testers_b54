package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

  public void removeContact() {
    openContactsPage();
    selectContact();
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

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

  private void fillContactForm(ContactData contact) {
    type(By.name("firstname"), contact.firstName());
    type(By.name("lastname"), contact.lastName());
    type(By.name("mobile"), contact.mobilePhone());
  }

  private void selectContact() {
    click(By.name("selected[]"));
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
}
